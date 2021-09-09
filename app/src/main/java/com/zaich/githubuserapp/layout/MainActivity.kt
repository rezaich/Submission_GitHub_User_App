package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.databinding.ActivityMainBinding
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


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

        adapter = UserAdapter(list ,this)
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvList.adapter = adapter
            rvList.setHasFixedSize(true)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    binding.searchView.clearFocus()
                    searchData(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        viewModel.getSearch().observe(this,{
            Log.i("mainActivity", "onCreate: $it")
            if (it != null){
                adapter.setSearchuser(it)
                showLoading(false)
            }else{
                Toast.makeText(this, "Pengguna tidak ada", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun searchData( query : String){
        binding.apply {
            showLoading(true)
            viewModel.setSearch(query)
        }
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.pbSearch.visibility= View.VISIBLE
        }else{
            binding.pbSearch.visibility= View.GONE
        }
    }
}