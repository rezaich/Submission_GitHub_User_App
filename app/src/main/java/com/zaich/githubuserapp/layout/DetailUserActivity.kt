package com.zaich.githubuserapp.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.databinding.ActivityDetailUserBinding
import com.zaich.githubuserapp.viewmodel.DetailUserViewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var viewPagerAdapter: SectionsPagerAdapter
    companion object{
        const val EXTRA_USER = "EXTRA USER"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_1 ,R.string.tab_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USER)
        val bundle= Bundle()
        bundle.putString(EXTRA_USER,username)

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

/*        val sectionsPagerAdapter = SectionsPagerAdapter(this,supportFragmentManager,bundle)
        with(binding){
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }*/
        viewPagerAdapter = SectionsPagerAdapter(supportFragmentManager,lifecycle)

        with(binding){
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout,viewPager){tab,position ->
                tab.text = resources.getString(TAB_TITLE[position])
            }.attach()
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