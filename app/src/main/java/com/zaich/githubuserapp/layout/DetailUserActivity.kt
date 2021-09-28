package com.zaich.githubuserapp.layout

import android.content.Intent
import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
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
//        val username = intent.getStringExtra(EXTRA_USER)

        val username = selectUser.username

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

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var check = false
        val selectUser = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel
        val id = selectUser.id
        CoroutineScope(Dispatchers.IO).launch {
            val countFavoriteUser = viewModel.checkFavoriteUsers(id)
            withContext(Dispatchers.Main) {
                if (countFavoriteUser != null) {
                    if (countFavoriteUser > 0) {
                        item.isChecked = true
                        check = true
                    } else {
                        item.isChecked = false
                        check = false
                        check = false
                    }
                }
            }
        }
/*        if (item.itemId == R.id.action_unfavorite_users) {
            if (!check){
                viewModel.addFavoriteUsers(selectUser.username,selectUser.id,selectUser.html_url,selectUser.avatar)
                Toast.makeText(this, "FAVORITE", Toast.LENGTH_SHORT).show()
            }else{
                item.setVisible(false)
            }
        }
        if (item.itemId == R.id.action_favorite_users){
            if (check){
                viewModel.removeFavoriteUsers(id)
                Toast.makeText(this, "Unfavorite", Toast.LENGTH_SHORT).show()
                item.isChecked = check
            }else {
                item.setVisible(false)
            }*/

//        }
        return super.onOptionsItemSelected(item)
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