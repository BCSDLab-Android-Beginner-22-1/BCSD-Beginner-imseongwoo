package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_READ_EXTERMAL_STORAGE = 1000

    var boardList = mutableListOf<BoardData>()
    private val boardAdapter = BoardAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val viewModelSingleton = ViewModelSingleton
        viewModelSingleton.viewModel = viewModel

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
                viewModel.deleteItem(position)
            }

        })

        viewModel.liveData.observe(this , Observer {
            boardAdapter.setData(viewModel.liveData.value!! as MutableList<BoardData>)
        })



    }

    fun checkPermission(){
        // 권한 부여 확인
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            // 권한 허용 안됨
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("권한 요청")
                    .setMessage("사진을 가져오려면 권한이 필수로 필요합니다.")
                    .setPositiveButton("OK"){
                            dialog, which -> // 권한 요청
                        ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERMAL_STORAGE)
                    }
                    .setNegativeButton("CANCEL",null)
                    .create()

                alertDialog.show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE)
            }
        } else {
            // 권한이 이미 허용됨
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        }
        }

}


