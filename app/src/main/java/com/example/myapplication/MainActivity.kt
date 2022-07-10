package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val boardAdapter = BoardAdapter()
    var boardList = mutableListOf<BoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val plusButton = findViewById<Button>(R.id.plus_button)

        boardList.add(0,BoardData("example","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example2","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))
        boardList.add(0,BoardData("example3","sw","내용 예시입니다."))

        boardAdapter.setMyItemClickListener(object : BoardAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,"click!",Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(position: Int) {
                Toast.makeText(this@MainActivity,"Long click!",Toast.LENGTH_SHORT).show()
            }

        })



        boardAdapter.boardList = boardList
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = boardAdapter
            setHasFixedSize(true)
        }
    }
}