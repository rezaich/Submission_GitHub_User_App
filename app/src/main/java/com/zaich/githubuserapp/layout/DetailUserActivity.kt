package com.zaich.githubuserapp.layout

import android.content.Intent
import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.databinding.ActivityDetailUserBinding
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.viewmodel.DetailUserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var viewPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val selectUser = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel

        val username = selectUser.username
        val id = selectUser.id

        val bundle = Bundle()
        bundle.putString(EXTRA_USER, username)


        supportActionBar?.title = username

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.setDetailUser(username)
        showLoading(true)
        viewModel.getDetailUser().observe(this, {
            if (it != null) {
                with(binding) {
                    Glide.with(this@DetailUserActivity).load(it.avatar).into(imgPhoto)
                    tvNameDetail.text = it.name
                    tvRepository.text = it.publicRepos.toString()
                    tvFollowing.text = it.following.toString()
                    tvFollower.text = it.followers.toString()
                    tvCompany.text = it.company.toString()
                    tvLocation.text = it.location.toString()
                    showLoading(false)

                    if (it.location.isNullOrEmpty()) {
                        llLocation.visibility = View.GONE
                    }
                    if (it.company.isNullOrEmpty()) {
                        llCompany.visibility = View.GONE
                    }
                }
            }
        })

        viewPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle, bundle)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLE[position])
            }.attach()
        }

        var checkFavoriteUser = false
        CoroutineScope(Dispatchers.IO).launch {
            val countFavoriteUser = viewModel.checkFavoriteUsers(id)
            withContext(Dispatchers.Main) {
                if (countFavoriteUser != null) {
                    binding.btnFavorite.apply {
                        if (countFavoriteUser > 0) {
                            binding.btnFavorite.isChecked = true
                            checkFavoriteUser = true
                        } else {
                            binding.btnFavorite.isChecked = false
                            checkFavoriteUser = false
                            checkFavoriteUser = false
                        }
                    }
                }
            }
        }

        binding.btnFavorite.setOnClickListener {
            checkFavoriteUser = !checkFavoriteUser
            with(viewModel) {
                binding.btnFavorite.apply {
                    Log.d("btnFavorite", id.toString())
                    if (checkFavoriteUser) {
                        addFavoriteUsers(username,id,selectUser.html_url,selectUser.avatar)
                        Toast.makeText(this@DetailUserActivity, "FAVORITE", Toast.LENGTH_SHORT).show()
                    } else {
                        removeFavoriteUsers(id)
                        Toast.makeText(this@DetailUserActivity, "UNFAVORITE", Toast.LENGTH_SHORT).show()
                        isChecked = checkFavoriteUser
                    }
                }
            }
        }

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbSearch.visibility = View.VISIBLE
        } else {
            binding.pbSearch.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_USER = "EXTRA USER"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_1, R.string.tab_2
        )
    }
}