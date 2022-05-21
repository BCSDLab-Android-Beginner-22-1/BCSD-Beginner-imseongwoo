package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private val linearLayout: LinearLayout by lazy {
        findViewById(R.id.linear_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<NavigationBarView>(R.id.bottomNavigationView)

        bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_one -> {
                        changeFragment(AFragment())
                    }
                    R.id.item_two -> {
                        changeFragment(BFragment())
                    }
                    R.id.item_three -> {
                        changeFragment(CFragment())
                    }
                }
                true
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.linear_layout, fragment)
            .commit()
    }
}
