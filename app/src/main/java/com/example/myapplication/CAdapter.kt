package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 26
    }

    override fun createFragment(position: Int): Fragment {
        val cFragment = CFragment()
        val bundle = Bundle()
        bundle.putInt("pos",position)
        cFragment.arguments = bundle
        return cFragment
    }

}