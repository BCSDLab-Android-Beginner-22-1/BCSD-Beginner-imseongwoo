package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<NavigationBarView>(R.id.bottom_navigation_view)
        val vPager = findViewById<ViewPager2>(R.id.my_viewpager)
        val adapterA = AAdapter(this)
        val adapterB = BAdapter(this)
        val adapterC = CAdapter(this)

        bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_one -> {
                        vPager.adapter = adapterA
                        vPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }
                    R.id.item_two -> {
                        vPager.adapter = adapterB
                        vPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }
                    R.id.item_three -> {

                        vPager.adapter = adapterC
                        vPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }
                }
                true
            }
        }

    }

}
