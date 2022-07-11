package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var boardList = mutableListOf<BoardData>()
    private val boardAdapter = BoardAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.addItem(BoardData("ex5", "sb", "내용 예시입니다.2"))

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            Log.d("callback","call back!")

        }
        val intent : Intent = Intent(this,BoardAddActivity::class.java)

        boardAdapter.setMyItemClickListener(object : BoardAdapter.MyItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "click!", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(position: Int) {
                viewmodel.deleteItem(position)


            }

        })

        binding.plusButton.setOnClickListener {
            requestLauncher.launch(intent)

        }

        boardAdapter.boardList = boardList
        binding.recyclerView.adapter = boardAdapter
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewmodel.liveData.observe(this , Observer {
            boardAdapter.setData(viewmodel.liveData.value!! as MutableList<BoardData>)
        })



    }


}