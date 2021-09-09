package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.databinding.FragmentFollowBinding
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.viewmodel.FollowerViewModel
import com.zaich.githubuserapp.viewmodel.FollowingViewModel


class FollowingFragment : Fragment(R.layout.fragment_follow) {
    private var list = arrayListOf<UserModel>()
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    private lateinit var _binding : FragmentFollowBinding
    private val binding get() =  _binding
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USER).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = activity?.let { UserAdapter(list, it) }!!
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollower.setHasFixedSize(true)
            rvFollower.layoutManager = LinearLayoutManager(activity)
            rvFollower.adapter = adapter
        }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)
        viewModel.setFollowings(username)
        viewModel.getFollowing().observe(viewLifecycleOwner,{
            if (it !=null){
                adapter.setSearchuser(it)
            }
        })
    }

}