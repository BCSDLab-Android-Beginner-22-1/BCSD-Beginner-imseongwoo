package com.example.myapplication

import android.net.Uri
import android.provider.MediaStore

//class Music(id:String, title:String?, artist:String?, albumId:String?, duration:Long?) {
//
//    var id: String = ""
//    var title: String? = ""
//    var artist: String? = ""
//    var albumId: String? = ""
//    var duration:Long? = 0
//
//    init {
//        this.id = id
//        this.title = title
//        this.artist = artist
//        this.albumId = albumId
//        this.duration = duration
//    }
//}

data class Music(
    val id: String,
    val title: String,
    val artist: String,
    val albumId: String,
    val duration: Long,

    )