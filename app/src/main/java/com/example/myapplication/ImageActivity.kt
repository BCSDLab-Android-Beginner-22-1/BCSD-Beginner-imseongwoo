package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ActivityImageBinding
import java.util.ArrayList
import com.example.myapplication.ViewModelSingleton.viewModel
class ImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityImageBinding
    private lateinit var  imageAdapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)

        getAllPhotos()

    }


    private fun getAllPhotos() {
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")
        val uriArr = ArrayList<String>()
        if(cursor!=null){
            while(cursor.moveToNext()){
                // 사진 경로 Uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                uriArr.add(uri)
            }
            cursor.close()
        }
        viewModel.setViewModelUriArr(uriArr)
        val adapter = ImageAdapter(this,uriArr)

        binding.imageRecyclerView.adapter = adapter
        binding.imageRecyclerView.setHasFixedSize(true)
        binding.imageRecyclerView.layoutManager = GridLayoutManager(this,5)



    }
}