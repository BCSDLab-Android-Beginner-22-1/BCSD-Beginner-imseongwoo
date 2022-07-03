package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var lapList = mutableListOf<LapData>()
    var job: Job? = null
    private lateinit var timeTextView: TextView
    private var lapNum = 1
    private val lapAdapter = LapAdapter()
    private var isStart = false
    private var startTime = 0L
    private var isNotPaused = true
    private var pauseTime: Long = 0L
    private var resumeTime: Long = 0L
    var nowTime = 0L


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.time_textview)
        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)
        val lapButton = findViewById<Button>(R.id.lap_button)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        startButton.setOnClickListener {
            if (!isStart) {
                startTime = System.currentTimeMillis()
                startTimer(startTime)
                startButton.text = getString(R.string.pause)
                this.isStart = true

            } else {
                if (isNotPaused) {
                    pauseTimer()
                    isNotPaused = !isNotPaused
                    startButton.text = getString(R.string.start)
                } else {
                    startTime = resumeTime
                    startTimer(resumeTime)
                    isNotPaused = !isNotPaused
                    startButton.text = getString(R.string.start)
                }
            }

        }


        stopButton.setOnClickListener {
            job?.cancel()
            startButton.text = getString(R.string.start)
            lapList.clear()
            lapAdapter.notifyDataSetChanged()
            timeTextView.text = getString(R.string.time)
            this.isStart = false
            lapNum = 1
        }

        lapButton.setOnClickListener {
            if (this.isStart) {
                lapList.add(0, LapData(lapNum, nowTime))
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

    private fun pauseTimer() {
        pauseTime = (System.currentTimeMillis() - startTime)

        timeTextView.text = pauseTime.displayTime()
        job = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                resumeTime = System.currentTimeMillis() - pauseTime
                delay(INTERVAL)
            }
        }
    }

    private fun startTimer(time: Long) {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isNotPaused) {
                nowTime = System.currentTimeMillis() - time
                timeTextView.text = nowTime.displayTime()
                delay(INTERVAL)
            }
        }

    }

    fun Long.displayTime(): String {
        if (this <= 0L) {
            return START_TIME
        }

        val m = this / 1000 % 3600 / 60
        val s = this / 1000 % 60
        val ms = this % 1000 / 10

        return String.format("%02d : %02d : %02d",
            m, s, ms)
    }

    
    companion object {
        const val START_TIME = "00:00:00"
        private const val INTERVAL = 10L
    }
}