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
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var boardList = mutableListOf<BoardData>()
    private val boardAdapter = BoardAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel : MainViewModel
    val date = Date()
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
    val currentTime = simpleDateFormat.format(date)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.addItem(BoardData("ex1", "sw", "내용 예시입니다.1","${currentTime}"))
        viewmodel.addItem(BoardData("ex2", "hk", "내용 예시입니다.2","${currentTime}"))
        viewmodel.addItem(BoardData("ex3", "ch", "내용 예시입니다.3","${currentTime}"))


        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            Log.d("callback","call back!")

        }
        val intent : Intent = Intent(this,BoardAddActivity::class.java)


        binding.plusButton.setOnClickListener {
            requestLauncher.launch(intent)

        }

        boardAdapter.boardList = boardList
        binding.recyclerView.adapter = boardAdapter
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        boardAdapter.setMyItemClickListener(object : BoardAdapter.MyItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "click!", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(position: Int) {
                viewmodel.deleteItem(position)
            }

        })

        viewmodel.liveData.observe(this , Observer {
            boardAdapter.setData(viewmodel.liveData.value!! as MutableList<BoardData>)
        })



    }


}