package com.zaich.githubuserapp.layout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.viewmodel.FollowerViewModel
import com.zaich.githubuserapp.databinding.FragmentFollowBinding
import com.zaich.githubuserapp.model.UserModel

class FollowerFragment : Fragment(R.layout.fragment_follow) {

    private var list = arrayListOf<UserModel>()
    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    private lateinit var _binding: FragmentFollowBinding
    private val binding get() = _binding


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

        showLoading(true)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowerViewModel::class.java)
        viewModel.setFollowers(username)
        viewModel.getFollower().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setSearchuser(it)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbSearch.visibility = View.VISIBLE
        } else {
            binding.pbSearch.visibility = View.GONE
        }
    }
}