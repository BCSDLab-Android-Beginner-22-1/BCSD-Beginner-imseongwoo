package com.example.myapplication

class MainRepository {
    val datalist = mutableListOf<BoardData>()

    fun removeData(position:Int): MutableList<BoardData> {
        datalist.removeAt(position)
        return datalist
    }

    fun addData(data: BoardData): MutableList<BoardData> {
        datalist.add(data)
        return datalist
    }
}