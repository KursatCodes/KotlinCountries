package com.muhammedkursat.kotlincountries.sevice

import com.muhammedkursat.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE>>> https://raw.githubusercontent.com/
    // EXT>>> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    private val BASE_URL = "https://raw.githubusercontent.com/"

    private var api = Retrofit.Builder()//Retrofit builderi calistiriyoruz
        .baseUrl(BASE_URL)//base url istiyor
        .addConverterFactory(GsonConverterFactory.create())//donusturucu varsa ekliyoruz
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//adapter varsa ekliyoruz
        .build()//build ediyoruz
        .create(CountryAPI::class.java)// olusturuyoruz

    fun getData():Single<List<Country>>{// daha sonra bir fonksiyon ile interface ile verileri cekiyoruz
        return api.getCountries()
    }


}