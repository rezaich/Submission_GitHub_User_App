package com.zaich.githubuserapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaich.githubuserapp.databinding.ItemLayoutBinding

class UserAdapter(private val list: ArrayList<UserModel>,val context: Context):RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(user:UserModel) {
            binding.tvName.text = user.name
            binding.tvUserName.text = user.user_name
            binding.imgPhoto.setImageResource(user.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener{
            val model = list.get(position)

            val intent = Intent(context,DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER,model)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size

}

/*class UserAdapter internal constructor(private val context: Context): BaseAdapter(){

    internal var user = arrayListOf<UserModel>()

    override fun getCount(): Int = user.size

    override fun getItem(position: Int): Any = user[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, view: View? , viewGroup: ViewGroup): View {
        var itemview = view
        if (itemview == null){
            itemview = LayoutInflater.from(context).inflate(
                R.layout.item_layout,viewGroup,false
            )
        }

        val viewHolder = ViewHolder(itemview as View)
        val user = getItem(position) as UserModel
        viewHolder.bind(user)

        itemview.setOnClickListener {
            val intent = Intent(context,DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.EXTRA_USER,user)
            context.startActivity(intent)
        }

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
}*/
