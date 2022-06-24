package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startProcess()
        } else {
            Toast.makeText(this, "권한 요청을 승인해야지만 앱을 실행할 수 있습니다.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private val permissions = Manifest.permission.READ_EXTERNAL_STORAGE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissionLauncher.launch(permissions)
    }

    fun startProcess(){
        val adapter = MusicAdapter()
        adapter.musicList.addAll(getMusicList())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun getMusicList(): List<Music> {
        val musicListUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        val cursor = contentResolver.query(musicListUri, proj, null, null, null)
        val musicList = mutableListOf<Music>()
        while (cursor?.moveToNext() ?: false) {
            val id = cursor!!.getString(0)
            val title = cursor!!.getString(1)
            val artist = cursor!!.getString(2)
            val albumId = cursor!!.getString(3)
            val duration = cursor!!.getLong(4)

            val music = Music(id, title, artist, albumId, duration)
            musicList.add(music)

        }
        cursor?.close()
        return musicList
    }
}