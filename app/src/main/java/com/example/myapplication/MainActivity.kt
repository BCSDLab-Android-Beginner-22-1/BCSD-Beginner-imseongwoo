package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(){

    var updateCount = 0
    private lateinit var countNum:TextView

    val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK) {
            updateCount = it.data!!.getIntExtra("countNum", 0)
            countNum.text = updateCount.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        countNum = findViewById<TextView>(R.id.id_count_num)
        val countBtn = findViewById<Button>(R.id.count_button)
        val toastBtn = findViewById<Button>(R.id.toast_button)
        val randomBtn = findViewById<Button>(R.id.random_button)


        toastBtn.setOnClickListener {
            Toast.makeText(this,"toast message",Toast.LENGTH_SHORT).show()
        }

        countBtn.setOnClickListener {
            count++
            countNum.text = count.toString()
        }

        randomBtn.setOnClickListener {
            val intent = Intent(this,RandomActivity::class.java)
            intent.putExtra("data",count)
            startActivity.launch(intent)
        }

    }

}