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
                val nowMinutes = TimeUnit.MILLISECONDS.toMinutes(time)
                val nowSeconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(nowMinutes)
                val nowMillis = time - TimeUnit.SECONDS.toMillis(nowSeconds) - TimeUnit.MINUTES.toMillis(nowMinutes)
                lapNumTextView.text = data.num.toString()
                lapTimeTextView.text = String.format("%02d : %02d : %02d",
                    nowMinutes,
                    nowSeconds,
                    nowMillis / 10)
            }
        }
    }


}