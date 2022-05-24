package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 11
    }

    override fun createFragment(position: Int): Fragment {

        val bFragment = BFragment()
        val bundle = Bundle()
        bundle.putInt("pos",position)
        bFragment.arguments = bundle

        return bFragment
    }

}