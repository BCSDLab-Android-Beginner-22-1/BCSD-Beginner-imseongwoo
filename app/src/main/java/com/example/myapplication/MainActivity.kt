package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var flag = false
    var lapList = mutableListOf<LapData>()
    var job: Job? = null
    private lateinit var timeTextView: TextView
    private var lapNum = 1
    private val lapAdapter = LapAdapter()
    private var time = 0L


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById<TextView>(R.id.time_textview)
        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)
        val lapButton = findViewById<Button>(R.id.lap_button)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        startButton.setOnClickListener {
            if (flag == true) {
                job?.cancel()
                startButton.text = getString(R.string.start)
                flag = false
            } else {
                timer()
                startButton.text = getString(R.string.pause)
                flag = true
            }

        }
        stopButton.setOnClickListener {
            job?.cancel()
            time = 0L
            startButton.text = getString(R.string.start)
            lapList.clear()
            lapAdapter.notifyDataSetChanged()
            timeTextView.text = getString(R.string.time)
            flag = false
            lapNum = 1
        }

        lapButton.setOnClickListener {
            if (flag) {
                lapList.add(0, LapData(lapNum, time))
                lapNum++
                lapAdapter.notifyDataSetChanged()
            }
        }

        lapAdapter.lapList = lapList
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lapAdapter
            setHasFixedSize(true)
        }


    }

    private fun timer() {
        flag = true
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(10L)
                timeTextView.text = convertTime(time)
                time += 10L
                Log.d("time","시간 : $time")
            }
        }
    }

    private fun convertTime(time: Long): String {
        val nowMinutes = TimeUnit.MILLISECONDS.toMinutes(time)
        val nowSeconds =
            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(nowMinutes)
        val nowMillis =
            time - TimeUnit.SECONDS.toMillis(nowSeconds) - TimeUnit.MINUTES.toMillis(nowMinutes)

        return String.format("%02d:%02d.%02d", nowMinutes, nowSeconds, nowMillis / 10)
    }
}