package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var liveData : MutableLiveData<List<BoardData>> = MutableLiveData<List<BoardData>>()
    private val datalist = mutableListOf<BoardData>()

    init {
        liveData.value = datalist
    }

    fun addItem(data: BoardData){
        datalist.add(0,data)
        liveData.value = datalist


    }

    fun deleteItem(position: Int) {
        datalist.removeAt(position)
        liveData.value = datalist

    }

}