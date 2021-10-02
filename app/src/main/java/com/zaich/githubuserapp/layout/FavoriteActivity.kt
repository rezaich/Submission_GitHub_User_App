package com.zaich.githubuserapp.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.database.Favorite
import com.zaich.githubuserapp.databinding.ActivityFavoriteBinding
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private var list = arrayListOf<UserModel>()
    private lateinit var viewModel : FavoriteViewModel
    private lateinit var adapter : FavoriteUserAdapter
    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "FAVORITE"

        adapter = FavoriteUserAdapter(list,this)
        adapter.notifyDataSetChanged()

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvList.adapter = adapter
            rvList.setHasFixedSize(true)
        }
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.getListFavoriteUsers()?.observe(this,{
            if (it != null){
                val mapList = mapList(it)
                adapter.setSearchuser(mapList)
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun mapList(favoriteUsers: List<Favorite>): ArrayList<UserModel> {
        val userList = ArrayList<UserModel>()
        for (users in favoriteUsers) {
            val mapUser = UserModel(users.login, users.id,users.html_url, users.avatarUrl)
            userList.add(mapUser)
        }
        return userList
    }
}