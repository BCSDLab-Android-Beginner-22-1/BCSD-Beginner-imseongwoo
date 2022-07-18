package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dj.loadingdialog.LoadingDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var liveData : MutableLiveData<List<BoardData>> = MutableLiveData<List<BoardData>>()
    var imgUriLiveData : MutableLiveData<String> = MutableLiveData<String>()


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

    fun setViewModelUriArr(uriArr : ArrayList<String>){
        mainRepository.setModelUriArr(uriArr)
    }

    fun setImgUriArr(str: String){
        imgUriLiveData.value = mainRepository.setModelImgUriArr(str)
    }


}