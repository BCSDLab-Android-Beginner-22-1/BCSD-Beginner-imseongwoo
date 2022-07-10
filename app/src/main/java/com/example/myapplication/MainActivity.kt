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
        boardList.add(0,BoardData("example3","sb","내용 예시입니다.3"))
        boardList.add(0,BoardData("example3","sc","내용 예시입니다.4"))
        boardList.add(0,BoardData("example3","sd","내용 예시입니다.5"))
        boardList.add(0,BoardData("example3","se","내용 예시입니다.6"))


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
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = boardAdapter
//            setHasFixedSize(true)
//        }
    }

    fun dataDelete(position: Int) {
        boardList.removeAt(position)
        boardAdapter.notifyItemRemoved(position)

    }
}