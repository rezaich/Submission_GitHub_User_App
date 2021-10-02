package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zaich.githubuserapp.database.Favorite
import com.zaich.githubuserapp.databinding.ItemFavoriteBinding
import com.zaich.githubuserapp.model.UserModel

class FavoriteUserAdapter(val list:ArrayList<UserModel> , val context: Context):RecyclerView.Adapter<FavoriteUserAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteUserAdapter.FavoriteViewHolder {
        return  FavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteUserAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
//            val model = list.get(position)
            val user = list[position]

            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER, user)
//            intent.putExtra(DetailUserActivity.EXTRA_USER,user.username)
            context.startActivities(arrayOf(intent))
        }
    }

    override fun getItemCount(): Int  = list.size


    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user:UserModel){
            with(binding){
                tvUserName.text = user.username
                tvUrlName.text = user.html_url
                Glide.with(itemView).load(user.avatar).into(imgPhoto)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchuser(users: ArrayList<UserModel>) {
        with(list) {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

}