package com.example.myapplication

import android.provider.MediaStore
import java.util.ArrayList

class MainRepository {

    val datalist = mutableListOf<BoardData>()
    var uriArray = ArrayList<String>()

    fun removeData(position:Int): MutableList<BoardData> {
        datalist.removeAt(position)
        return datalist
    }

    fun addData(data: BoardData): MutableList<BoardData> {
        datalist.add(data)
        return datalist
    }

    fun setModelUriArr(uriArr : ArrayList<String>){
        uriArray = uriArr
    }


}