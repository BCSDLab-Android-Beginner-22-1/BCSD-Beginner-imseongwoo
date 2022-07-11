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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)
        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        var adapter = BoardAdapter()

        binding.submitButton.setOnClickListener {
//            boardAdapter.addItem(BoardData(binding.addTitle.text.toString(),binding.addWriterContentTextview.text.toString(),binding.addBoardContentTextview.text.toString()))
            viewmodel.addItem(BoardData(binding.addTitle.text.toString(),binding.addWriterContentTextview.text.toString(),binding.addBoardContentTextview.text.toString()))
            viewmodel.liveData.observe(this , Observer {
                adapter.setData(viewmodel.liveData.value!! as MutableList<BoardData>)
            })
            Log.d("button","button click! ${binding.addTitle.text.toString()} ${binding.addWriterContentTextview.text.toString()} ${binding.addBoardContentTextview.text.toString()}")
            finish()
        }




    }
}