package com.zaich.githubuserapp.layout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.database.SettingPreferences
import com.zaich.githubuserapp.databinding.ActivitySettingBinding
import com.zaich.githubuserapp.databinding.ActivitySplashScreenBinding
import com.zaich.githubuserapp.viewmodel.SettingViewModel
import com.zaich.githubuserapp.viewmodel.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}