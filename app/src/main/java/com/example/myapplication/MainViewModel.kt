package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var liveData : MutableLiveData<List<BoardData>> = MutableLiveData<List<BoardData>>()
    private val mainRepository = MainRepository()

    init {
        liveData.value = mainRepository.datalist
    }

    fun addItem(data: BoardData){
        liveData.value = mainRepository.addData(data)


    }

    fun deleteItem(position: Int) {

        liveData.value = mainRepository.removeData(position)

    }

}