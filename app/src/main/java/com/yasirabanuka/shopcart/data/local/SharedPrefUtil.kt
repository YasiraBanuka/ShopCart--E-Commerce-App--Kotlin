package com.yasirabanuka.shopcart.data.local

import android.content.SharedPreferences
import com.yasirabanuka.shopcart.utils.Constants
import javax.inject.Inject


class SharedPrefUtil @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun isFirstAppLaunch(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_FIRST_APP_LAUNCH, true)
    }

    fun saveFirstAppLaunch(value: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.IS_FIRST_APP_LAUNCH, value).apply()
    }

    fun userIsLoggedIn(): Boolean {
        val token = sharedPreferences.getString(Constants.USER_IS_LOGGED_IN, null)
        return token != null
    }

    fun saveUserAccessToken(token: String) {
        sharedPreferences.edit().putString(Constants.USER_IS_LOGGED_IN, token).apply()
    }

    fun deleteAccessToken(): Boolean {
        sharedPreferences.edit().remove(Constants.USER_IS_LOGGED_IN).apply()
        return userIsLoggedIn()
    }
}