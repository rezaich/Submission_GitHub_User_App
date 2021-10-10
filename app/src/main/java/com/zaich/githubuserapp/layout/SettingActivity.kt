package com.zaich.githubuserapp.layout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.database.SettingPreferences
import com.zaich.githubuserapp.databinding.ActivitySettingBinding
import com.zaich.githubuserapp.viewmodel.SettingViewModel
import com.zaich.githubuserapp.viewmodel.ViewModelFactory
import java.util.*

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val SettingViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        SettingViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->

                if (isDarkModeActive ) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    binding.switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    binding.switchTheme.isChecked = false
                }
            })


//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            SettingViewModel.saveThemeSetting(isChecked)
        }
        binding.setLang.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }
}