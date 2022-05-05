package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.*

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_random)

        val countMainNum = intent.getIntExtra("data",0)
        val ranNumTextView = findViewById<TextView>(R.id.textview_random)
        val randomNum = Random().nextInt(countMainNum+1)

        ranNumTextView.text = randomNum.toString()

        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("countNum",randomNum)
        setResult(RESULT_OK,intent)

    }

}