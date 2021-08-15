package com.zaich.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.zaich.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding

    companion object{
        const val EXTRA_USER= "EXTRA USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val selectUser = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel

        binding.tvNameDetail.text = selectUser.name
        binding.imgPhoto.setImageResource(selectUser.avatar)
        binding.tvFollower.text = selectUser.follower
        binding.tvFollowing.text = selectUser.following
        binding.tvRepository.text = selectUser.repository
        binding.tvLocation.text = selectUser.location
        binding.tvCompany.text = selectUser.company

        actionBar.setTitle(selectUser.user_name)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}