package com.muhammedkursat.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedkursat.kotlincountries.model.Country
import com.muhammedkursat.kotlincountries.sevice.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel:ViewModel() {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countryList = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshCountries(){
        val country1 = Country("Türkiye","World","Ankara","www.ss.com","TRY","Turkish")
        val country2 = Country("Azerbaycan","Asia","Bakü","www.ss.com","AZR","Turkish")
        val country3 = Country("Filistin","Asia","Kudüs","www.ss.com","DNR","Arabic")

        var countries = arrayListOf<Country>(country1,country2,country3)
        countryList.value = countries
        countryError.value = false
        countryLoading.value = false
    }
    fun refreshData(){
        getDataFromAPI()
    }

    fun getDataFromAPI(){
        countryLoading.value = true

        countryApiService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    value?.let {
                        countryList.value = it
                        countryError.value = false
                        countryLoading.value = false
                    }
                }
                override fun onError(e: Throwable?) {
                    countryError.value = true
                    countryLoading.value = false
                    e!!.printStackTrace()
                }

            })

    }
}