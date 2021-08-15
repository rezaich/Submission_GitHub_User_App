package com.zaich.githubuserapp

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.zaich.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private var list = arrayListOf<UserModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar:ActionBar?=supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setIcon(R.mipmap.ic_github_foreground)

        showListView()
        prepare()
        addItem()

    }

    private fun showListView(){
        adapter = UserAdapter(this)
        binding.lvList.adapter = adapter
    }

    private fun prepare(){
        dataName= resources.getStringArray(R.array.name)
        dataUserName= resources.getStringArray(R.array.username)
        dataPhoto=resources.obtainTypedArray(R.array.avatar)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataRepository = resources.getStringArray(R.array.repository)
        dataLocation = resources.getStringArray(R.array.location)
        dataCompany=resources.getStringArray(R.array.company)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val User = UserModel(
                dataName[position],
                dataUserName[position],
                dataPhoto.getResourceId(position, -1),
                dataFollower[position],
                dataFollowing[position],
                dataRepository[position],
                dataLocation[position],
                dataCompany[position]
            )
            list.add(User)
        }
        adapter.user = list
    }
}