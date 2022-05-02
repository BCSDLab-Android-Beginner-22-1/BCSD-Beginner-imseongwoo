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

        val count_num = intent.getStringExtra("Data")
        val num = findViewById<TextView>(R.id.textview_random)

        // 랜덤 숫자 생성
        val int_random = Random().nextInt(count_num!!.toInt())
        num.text = int_random.toString()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val num = findViewById<TextView>(R.id.textview_random)

        val random_num = num.text
        val intent = Intent()
        intent.putExtra("data",random_num)
        setResult(Activity.RESULT_OK,intent)
        Log.e("random num", random_num.toString())
        Log.e("intent", intent.toString())
        finish()
        Log.d("ran","onbackpressed 끝")


    }
}