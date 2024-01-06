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