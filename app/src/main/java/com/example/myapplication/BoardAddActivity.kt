package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityBoardAddBinding
import com.example.myapplication.databinding.ActivityMainBinding

class BoardAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardAddBinding
    private val boardAdapter = BoardAdapter()
    private lateinit var board: BoardData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)

        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        var adapter = BoardAdapter()

        binding.submitButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val writer = binding.addWriterContentTextview.text.toString()
            val board = binding.addBoardContentTextview.text.toString()
            val time = binding.addTimeContentTextview.text.toString()

            viewmodel.addItem(BoardData(title,writer,board,time))
            viewmodel.liveData.observe(this , Observer {
                adapter.setData(viewmodel.liveData.value!! as MutableList<BoardData>)

            })
            finish()
        }


    }
}