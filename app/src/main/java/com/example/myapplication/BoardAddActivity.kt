package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityBoardAddBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ViewModelSingleton.viewModel


class BoardAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardAddBinding
    private val boardAdapter = BoardAdapter()
    private lateinit var board: BoardData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)

        var adapter = BoardAdapter()

        binding.submitButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val writer = binding.addWriterContentTextview.text.toString()
            val board = binding.addBoardContentTextview.text.toString()
            val time = binding.addTimeContentTextview.text.toString()

            viewModel.addItem(BoardData(title,writer,board,time))
            finish()
        }


    }
}