package com.muhammedkursat.kotlincountries.sevice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.muhammedkursat.kotlincountries.model.Country

@Dao
interface CountryDao {
    // Data Access Object
    @Insert
    suspend fun insertAll(vararg countries : Country): List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuID = :countryID")
    suspend fun getCountry(countryID: Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
    
    @Delete
    suspend fun deleteCountry(countryID: Int)
}