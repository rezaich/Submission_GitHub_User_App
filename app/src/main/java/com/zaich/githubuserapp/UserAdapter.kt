package com.zaich.githubuserapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaich.githubuserapp.databinding.ItemLayoutBinding

class UserAdapter internal constructor(private val context: Context): BaseAdapter(){

    internal var user = arrayListOf<UserModel>()

    override fun getCount(): Int = user.size

    override fun getItem(position: Int): Any = user[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, view: View? , viewGroup: ViewGroup ): View {
        var itemview = view
        if (itemview == null){
            itemview = LayoutInflater.from(context).inflate(
                R.layout.item_layout,viewGroup,false
            )
        }

        val viewHolder = ViewHolder(itemview as View)

        val user = getItem(position) as UserModel
        viewHolder.bind(user)
        return itemview
    }

    private inner class ViewHolder constructor(view: View){
        private val binding = ItemLayoutBinding.bind(view)

        fun bind(user:UserModel) {
            binding.tvName.text = user.name
            binding.tvUserName.text = user.user_name
            binding.imgPhoto.setImageResource(user.avatar)
        }
    }

}

/**
 * class UserAdapter(val listUser: ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
val binding = ItemLayoutBinding.bind(view)

init {
binding.tvName.text =
binding.imgPhoto.setImageResource(userModel.avatar)
binding.tvUserName.text = userModel.user_name
}

}

override fun getItemCount(): Int = listUser.size

override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
val view :View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_layout,viewGroup,false)
return  ViewHolder(view)
}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val user : UserModel = listUser[position]
Intent
}
}
 */
