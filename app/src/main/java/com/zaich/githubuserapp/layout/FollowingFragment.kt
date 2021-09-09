package com.zaich.githubuserapp.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zaich.githubuserapp.R
import com.zaich.githubuserapp.databinding.FragmentFollowBinding


class FollowingFragment : Fragment() {
    private var _binding : FragmentFollowBinding? = null
    private val binding get() =  _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding?.root
    }

}