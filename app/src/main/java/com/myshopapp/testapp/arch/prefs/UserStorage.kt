package com.myshopapp.testapp.arch.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStorage @Inject constructor(
    @ApplicationContext
    val context: Context
) {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            "user_session",
            Context.MODE_PRIVATE
        )
    }

    fun setUserSession(userEmail: String) {
        sharedPreferences.edit {
            putString("user_email", userEmail)
            putBoolean("is_logged", true)
        }
    }


    fun setUserLogout() {
        sharedPreferences.edit().clear().apply()
    }


    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged", false)
    }
}