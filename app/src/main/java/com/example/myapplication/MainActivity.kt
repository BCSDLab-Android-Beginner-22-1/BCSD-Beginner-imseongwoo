package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val boardAdapter = BoardAdapter()
    var boardList = mutableListOf<BoardData>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        boardList.add(0,BoardData("example","sw","내용 예시입니다1."))
        boardList.add(0,BoardData("ex","sa","내용 예시입니다.2"))
        boardList.add(0,BoardData("ex3","sb","내용 예시입니다.3"))
        boardList.add(0,BoardData("ex3","sc","내용 예시입니다.4"))
        boardList.add(0,BoardData("e3","sd","내용 예시입니다.5"))
        boardList.add(0,BoardData("ex3","se","내용 예시입니다.6"))
        boardList.add(0,BoardData("exa4","sc","내용 예시입니다.7"))
        boardList.add(0,BoardData("ex5","sd","내용 예시입니다.8"))
        boardList.add(0,BoardData("exa6","se","내용 예시입니다.9"))
        boardList.add(0,BoardData("exa7","sc","내용 예시입니다.10"))
        boardList.add(0,BoardData("ex9","sd","내용 예시입니다.11"))
        boardList.add(0,BoardData("ex10","se","내용 예시입니다.12"))


        boardAdapter.setMyItemClickListener(object : BoardAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,"click!",Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(position: Int) {
                Toast.makeText(this@MainActivity,"Long click!",Toast.LENGTH_SHORT).show()
                dataDelete(position)
            }

        })

        boardAdapter.boardList = boardList
        binding.recyclerView.adapter = boardAdapter
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun dataDelete(position: Int) {
        boardList.removeAt(position)
        boardAdapter.notifyItemRemoved(position)

    }
}