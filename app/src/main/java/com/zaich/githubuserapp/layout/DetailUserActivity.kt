package com.zaich.githubuserapp.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zaich.githubuserapp.databinding.ActivityDetailUserBinding
import com.zaich.githubuserapp.viewmodel.DetailUserViewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    companion object{
        const val EXTRA_USER = "EXTRA USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USER)
        supportActionBar?.title = username

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setDetailUser(username!!)
        viewModel.getDetailUser().observe(this,{
            if (it != null){
                with(binding){
                    Glide.with(this@DetailUserActivity).load(it.avatar).into(imgPhoto)
                    tvNameDetail.text = it.name
                    tvRepository.text = it.public_repos.toString()
                    showLoading(false)
                }
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this,supportFragmentManager)
        with(binding){
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.pbSearch.visibility= View.VISIBLE
        }else{
            binding.pbSearch.visibility= View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}