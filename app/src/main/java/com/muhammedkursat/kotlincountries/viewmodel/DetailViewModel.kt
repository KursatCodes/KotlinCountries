package com.muhammedkursat.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
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
        getCountriesFromDB(position)

    }
    private fun getCountriesFromDB(position : Int){
        launch {
            var country = CountryDatabase(getApplication()).countryDao().getCountry(position)
            showCountryees(country)
            Toast.makeText(getApplication(),"from SQL", Toast.LENGTH_SHORT).show()
        }
    }
    fun showCountryees(country : Country){
        countryLiveData.value = country
    }
}