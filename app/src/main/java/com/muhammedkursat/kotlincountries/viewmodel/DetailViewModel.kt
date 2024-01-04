package com.muhammedkursat.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedkursat.kotlincountries.model.Country
import com.muhammedkursat.kotlincountries.sevice.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()
    fun refreshDetails(position:Int){
        val country = Country("Türkiye","World","Ankara","www.ss.com","TRY","Turkish")
        val country2 = Country("Azerbaycan","Asia","Bakü","www.ss.com","AZR","Turkish")
        val country3 = Country("Filistin","Asia","Kudüs","www.ss.com","DNR","Arabic")

        var countries = arrayListOf<Country>(country,country2,country3)
        countryLiveData.value = getCountriesFromDB(position)

    }
    private fun getCountriesFromDB(position: Int):Country{
        var country = Country("","","","","","")
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            country = dao.getCountry(position)
            countryLiveData.value = getCountriesFromDB(position)
        }

        return country
    }
}