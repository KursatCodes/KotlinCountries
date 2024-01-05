package com.muhammedkursat.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedkursat.kotlincountries.model.Country
import com.muhammedkursat.kotlincountries.sevice.CountryAPIService
import com.muhammedkursat.kotlincountries.sevice.CountryDatabase
import com.muhammedkursat.kotlincountries.util.CustomSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application): BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreference = CustomSharedPreference(getApplication())
    private val timeLimit = 0.1 * 60 * 1000 * 1000 * 1000L

    val countryList = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshCountries(){
        val country1 = Country("Türkiye","World","Ankara","www.ss.com","TRY","Turkish")
        val country2 = Country("Azerbaycan","Asia","Bakü","www.ss.com","AZR","Turkish")
        val country3 = Country("Filistin","Asia","Kudüs","www.ss.com","DNR","Arabic")

        val countries = arrayListOf<Country>(country1,country2,country3)
        countryList.value = countries
        countryError.value = false
        countryLoading.value = false
    } // test icin yapilan fonksiyon
    fun refreshData(){
        var updateTime = customSharedPreference?.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < timeLimit){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }

    }
    fun getDataFromSQLite(){
        launch {
            val countryes = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countryes)
            Toast.makeText(getApplication(),"from SQL",Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromAPI(){
        Toast.makeText(getApplication(),"from INTERNET",Toast.LENGTH_LONG).show()
        countryLoading.value = true // pencere acilir acilmaz proggressbar gorunsun

        disposable.add(// kullan at object
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        value?.let {
                            storeInSQLite(it)
                        }
                    }
                    override fun onError(e: Throwable?) {
                        countryError.value = true
                        countryLoading.value = false
                        e!!.printStackTrace()
                    }
                })
        )
    }
    private fun showCountries(mycountryList : List<Country>){
        countryList.value = mycountryList
        countryError.value = false
        countryLoading.value = false
    }
    private fun storeInSQLite(list : List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())// diziyi tek tek veriyor kotline ozel ;)
            var i = 0
            while (i < list.size){
                list[i].uuID = listLong[i].toString().toInt()
                i++
            }
            showCountries(list)
        }
        customSharedPreference.saveTime(System.nanoTime())//zamani kaydediyoruz
    }
}