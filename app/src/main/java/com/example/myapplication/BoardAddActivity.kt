package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dj.loadingdialog.LoadingDialog
import com.example.myapplication.databinding.ActivityBoardAddBinding
import com.example.myapplication.ViewModelSingleton.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class BoardAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardAddBinding
    val imgList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
        val currentTime = simpleDateFormat.format(date)

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            Log.d("callback", "call back!")

        }
        val intent: Intent = Intent(this, ImageActivity::class.java)

        binding.submitButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val writer = binding.addWriterContentTextview.text.toString()
            val board = binding.addBoardContentTextview.text.toString()
            val imgUri = imgList[0]
            viewModel.addItem(BoardData(title, writer, board, currentTime, imgUri))
            finish()
        }

        binding.imageButton.setOnClickListener {
            showLoadingDialog()
            CoroutineScope(Main).launch {
                delay(1000)
                requestLauncher.launch(intent)
            }

        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteImg()
        }

        viewModel.imgUriLiveData.observe(this, Observer {
            imgList.add(0, it)
            val imageView = binding.imageView
            ImageView.ScaleType.CENTER_CROP.also { imageView.scaleType = it }
            Glide.with(this).load(it).into(imageView)

        })


    }

    private fun showLoadingDialog() {
        val dialog = LoadingDialog(this)
        CoroutineScope(Main).launch {
            dialog.show()
            delay(1000)
            dialog.dismiss()

        }
    }
}