package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RandomActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    var randomNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val currentCount = intent.getIntExtra("count", 0)
        val randomText = findViewById<TextView>(R.id.random_textview)
        randomNum = Random().nextInt(currentCount.toInt() + 1)
        randomText.text = randomNum.toString()

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("countNum", randomNum)
        setResult(RESULT_OK, intent)
    }


}