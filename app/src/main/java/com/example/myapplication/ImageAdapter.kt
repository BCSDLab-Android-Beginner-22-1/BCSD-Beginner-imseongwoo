package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemImageBinding
import com.example.myapplication.ViewModelSingleton.viewModel

class ImageAdapter(val context: Context, uriArr:ArrayList<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private lateinit var itemImageBinding: ItemImageBinding
    var items = ArrayList<String>()
    init {
        this.items = uriArr
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemImageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_image,
            parent,
            false)

        return ViewHolder(itemImageBinding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.binding.recyclerviewImg
        val display = context.getResources().getDisplayMetrics()
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = LinearLayout.LayoutParams(display.widthPixels/3,display.widthPixels/3)
        Glide.with(context).load(items[position]).into(imageView)

    }

    fun setData(newData:MutableList<String>){
        items = newData as ArrayList<String>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.recyclerviewImg.setOnClickListener {
                val pos = adapterPosition
                viewModel.setImgUriArr(items[pos])


            }
        }
    }
}