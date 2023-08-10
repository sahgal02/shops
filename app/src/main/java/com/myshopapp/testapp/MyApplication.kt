package com.myshopapp.testapp

import android.app.Application
import com.myshopapp.testapp.arch.prefs.UserStorage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var userStorage: UserStorage

    override fun onCreate() {
        super.onCreate()
        instance = this
        isUserLoggedIn = userStorage.isUserLoggedIn()
    }

    companion object{
        var isUserLoggedIn = false
        var instance : MyApplication? = null
    }
}