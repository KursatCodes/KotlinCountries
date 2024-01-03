package com.muhammedkursat.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application : Application) : AndroidViewModel(application),CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // isini yap sonra maine geri don ne olur geri dooon

    override fun onCleared() { //application context bulunamazsa job'u iptal et.
        super.onCleared()
        job.cancel()
    }
}