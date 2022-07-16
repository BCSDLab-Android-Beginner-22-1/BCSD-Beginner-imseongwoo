package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide


class GridViewAdapter(val context: Context, uriArr:ArrayList<String>) : BaseAdapter(){
    private var items = ArrayList<String>()
    init {
        this.items = uriArr
    }
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(context)
        val display = context.getResources().getDisplayMetrics()
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = LinearLayout.LayoutParams(display.widthPixels/3,display.widthPixels/3)
        Glide.with(context).load(items[position]).into(imageView)

        imageView.setOnClickListener{

            val returnIntent = Intent(context, BoardAddActivity::class.java)

            returnIntent.putExtra("URI", items[position])

        }

        return imageView
    }


}