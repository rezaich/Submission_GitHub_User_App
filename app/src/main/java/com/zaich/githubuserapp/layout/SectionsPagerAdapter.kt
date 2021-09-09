package com.zaich.githubuserapp.layout

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zaich.githubuserapp.R

class SectionsPagerAdapter(private val context: Context, supportFragmentManager: FragmentManager):FragmentPagerAdapter(supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    @StringRes
    private val TAB_TITLE = intArrayOf(R.string.tab_1,R.string.tab_2)

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment:Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment()
            1->fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLE[position])
    }
}