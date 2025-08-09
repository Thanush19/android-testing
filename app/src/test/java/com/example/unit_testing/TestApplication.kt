package com.example.unit_testing

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.Base_Theme_Unittesting)
    }
}