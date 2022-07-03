package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LapAdapter.*
import java.util.concurrent.TimeUnit

class LapAdapter : RecyclerView.Adapter<ViewHolder>() {

    var lapList = mutableListOf<LapData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_laptime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(lapList[position], holder)

    }

    override fun getItemCount(): Int {
        return lapList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lapNumTextView = itemView.findViewById<TextView>(R.id.lap_num_textview)
        val lapTimeTextView = itemView.findViewById<TextView>(R.id.laptime_textview)

        fun bind(data: LapData, holder: ViewHolder) {
            with(holder) {
                val time = data.lap
                val m = time / 1000 % 3600 / 60
                val s = time / 1000 % 60
                val ms = time % 1000 / 10
                lapNumTextView.text = data.num.toString()
                lapTimeTextView.text = String.format("%02d : %02d : %02d",
                    m, s, ms)
            }
        }
    }


}