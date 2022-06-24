package com.example.myapplication

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class MusicAdapter : RecyclerView.Adapter<Holder>() {

    val musicList = mutableListOf<Music>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private lateinit var musicUri: Uri
    val imageAlbum = itemView.findViewById<ImageView>(R.id.imageAlbum)
    val textArtist = itemView.findViewById<TextView>(R.id.textArtist)
    val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
    val textDuration = itemView.findViewById<TextView>(R.id.textDuration)

    fun setMusic(music:Music){
        musicUri = music.getMusicUri()

        imageAlbum.setImageURI(music.getAlbumUri())
        textArtist.text = music.artist
        textTitle.text = music.title
        val sdf = SimpleDateFormat("HH:mm:ss")
        textDuration.text = sdf.format(music.duration)

    }
}