package com.muhammedkursat.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedkursat.kotlincountries.model.Country

class DetailViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()
    fun refreshDetails(position:Int){
        val country = Country("Türkiye","World","Ankara","www.ss.com","TRY","Turkish")
        val country2 = Country("Azerbaycan","Asia","Bakü","www.ss.com","AZR","Turkish")
        val country3 = Country("Filistin","Asia","Kudüs","www.ss.com","DNR","Arabic")

        var countries = arrayListOf<Country>(country,country2,country3)
        countryLiveData.value = countries.get(position)
    }
}