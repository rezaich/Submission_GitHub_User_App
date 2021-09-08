package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zaich.githubuserapp.databinding.ItemLayoutBinding
import com.zaich.githubuserapp.model.UserModel

class UserAdapter(private val list: ArrayList<UserModel>, val context: Context):RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            binding.tvName.text = user.username
            binding.tvUserName.text = user.html_url
            Glide.with(itemView).load(user.avatar).into(binding.imgPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            val username = list[position]

            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER,username.username )

            context.startActivities(arrayOf(intent))
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchuser(users: ArrayList<UserModel>){
        with(list){
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }
}

