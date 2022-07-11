package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var liveData : MutableLiveData<List<BoardData>> = MutableLiveData<List<BoardData>>()
    private val datalist = mutableListOf<BoardData>()

    init {
        var boardData = arrayListOf<BoardData>()
        boardData.add(BoardData("ex", "sa", "내용 예시입니다.1"))
        boardData.add(BoardData("ex2", "sb", "내용 예시입니다.2"))
        boardData.add(BoardData("ex2", "sb", "내용 예시입니다.2"))

        liveData.postValue(boardData)
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