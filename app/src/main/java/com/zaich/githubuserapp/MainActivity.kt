package com.zaich.githubuserapp

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zaich.githubuserapp.databinding.ActivityMainBinding
import com.zaich.githubuserapp.databinding.ItemLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var list = arrayListOf<UserModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val User = UserModel(
                dataName[position],
                dataUserName[position],
                dataPhoto.getResourceId(position, -1),
            )
            list.add(User)
        }
        adapter.user = list
    }
}