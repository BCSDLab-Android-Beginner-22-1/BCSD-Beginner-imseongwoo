package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private val musicAdapter = MusicAdapter()
    lateinit var nowMusic: Music

    @RequiresApi(Build.VERSION_CODES.M)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> startProcess()
                else -> {
                    when (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        true -> permissionDialog(true)
                        else -> permissionDialog(false)
                    }
                }
            }
        }

    private val permissions = Manifest.permission.READ_EXTERNAL_STORAGE

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissionLauncher.launch(permissions)
    }

    fun startProcess(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MusicAdapter()
        val musicData = getMusicList()
        adapter.musicList.addAll(getMusicList())


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setMyItemClickListener(object :MusicAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,"$position",Toast.LENGTH_SHORT).show()

//                createNotification(musicData)
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
                    )
                    setDataSource(applicationContext,
                        Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, musicData[position].id))
                    prepare()
                    start()
                }
//                onMediaStateChangeListener.onMediaStateChange(true)
            }

        })


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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun permissionDialog(isDenied: Boolean) {
        when (isDenied) {
            true -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("권한 요청")
                    .setMessage("허용 버튼을 눌러주세요.")
                    .setPositiveButton("확인") { _, _ ->
                        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .setCancelable(false)
                builder.show()
            }

        }
    }
}