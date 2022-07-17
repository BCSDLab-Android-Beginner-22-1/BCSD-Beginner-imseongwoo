package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityBoardAddBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ViewModelSingleton.viewModel
import java.text.SimpleDateFormat
import java.util.*


class BoardAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
        val currentTime = simpleDateFormat.format(date)

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            Log.d("callback","call back!")
//            val data: Intent? = it.data
//            val uri = data?.getStringExtra("URI").toString()
//
//            Toast.makeText(this, uri,Toast.LENGTH_SHORT).show()

        }
        val intent : Intent = Intent(this,ImageActivity::class.java)

        binding.submitButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val writer = binding.addWriterContentTextview.text.toString()
            val board = binding.addBoardContentTextview.text.toString()
            viewModel.addItem(BoardData(title, writer, board, currentTime))
            finish()
        }

        binding.imageButton.setOnClickListener {
            requestLauncher.launch(intent)
        }

        viewModel.imgUriLiveData.observe(this , Observer {
            val imageView = binding.imageView
            ImageView.ScaleType.CENTER_CROP.also { imageView.scaleType = it }
            Glide.with(this).load(it).into(imageView)

        })


    }
}