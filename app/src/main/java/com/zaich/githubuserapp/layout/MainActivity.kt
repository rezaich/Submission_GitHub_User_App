package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.database.SettingPreferences
import com.zaich.githubuserapp.databinding.ActivityMainBinding
import com.zaich.githubuserapp.databinding.ActivitySettingBinding
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.viewmodel.FavoriteViewModel
import com.zaich.githubuserapp.viewmodel.MainViewModel
import com.zaich.githubuserapp.viewmodel.SettingViewModel
import com.zaich.githubuserapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private var list = arrayListOf<UserModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setIcon(R.mipmap.ic_github_foreground)

        adapter = UserAdapter(list, this)
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getSearch().observe(this, {
            if (it != null) {
                adapter.setSearchuser(it)
                showLoading(false)
            } else {
                binding.layoutOk.layoutEmpty.visibility = View.VISIBLE
            }
        })



        binding.apply {
            rvList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvList.adapter = adapter
            rvList.setHasFixedSize(true)


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isEmpty()) {
                        layoutOk.layoutEmpty.visibility = View.VISIBLE
                        rvList.visibility = View.GONE
                    } else {
                        searchData(query)
                        layoutOk.layoutEmpty.visibility = View.GONE
                        rvList.visibility = View.VISIBLE
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        layoutOk.layoutEmpty.visibility = View.VISIBLE
                        rvList.visibility = View.GONE
                    } else {
                        layoutOk.layoutEmpty.visibility = View.GONE
                        rvList.visibility = View.VISIBLE
                    }
                    return false
                }
            })
        }
    }


    private fun searchData(query: String) {
        binding.apply {
            showLoading(true)
            viewModel.setSearch(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbSearch.visibility = View.VISIBLE
        } else {
            binding.pbSearch.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_change_settings) {
           startActivity(Intent(this,SettingActivity::class.java))
        }
        if (item.itemId == R.id.action_favorite_users){
            startActivity(Intent(this,FavoriteActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null && imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}