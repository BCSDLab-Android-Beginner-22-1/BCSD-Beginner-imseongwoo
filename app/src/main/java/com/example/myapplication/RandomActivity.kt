package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

class RandomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        val countMainNum = intent.getIntExtra("data", 0)
        setDataAtFragment(RandomFragment(), countMainNum.toString())
    }

    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    fun setDataAtFragment(fragment: Fragment, count: String) {
        val bundle = Bundle()
        bundle.putString("count", count)
        fragment.arguments = bundle
        setFragment(fragment)
    }

    fun receiveData(data: String) {
        Log.d("receive", "전달데이터:${data}")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("countNum", data.toInt())
        setResult(RESULT_OK, intent)
        finish()
    }

}