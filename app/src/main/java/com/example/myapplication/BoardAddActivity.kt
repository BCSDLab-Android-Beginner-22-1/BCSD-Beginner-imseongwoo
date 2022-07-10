package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityBoardAddBinding
import com.example.myapplication.databinding.ActivityMainBinding

class BoardAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardAddBinding
    private val boardAdapter = BoardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)

        binding.submitButton.setOnClickListener {
            boardAdapter.addItem(BoardData(binding.addTitle.text.toString(),binding.addWriterContentTextview.text.toString(),binding.addBoardContentTextview.text.toString()))
            finish()
        }


    }
}