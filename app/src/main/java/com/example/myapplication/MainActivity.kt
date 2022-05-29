package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.setFragmentResultListener
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val NOTIFICATION_ID = 1001
        const val NOTIFICATION_ID_2 = 1002
    }

    private lateinit var countNum: TextView
    var updateCount = 0


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countBtn = findViewById<Button>(R.id.count_button)
        val toastBtn = findViewById<Button>(R.id.toast_button)
        val randomBtn = findViewById<Button>(R.id.random_button)
        var count: Int = 0
        countNum = findViewById<TextView>(R.id.id_count_num)

        toastBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert Dialog!")
                .setMessage("메세지 내용 부분")
                .setPositiveButton("positive",
                    DialogInterface.OnClickListener { dialog, id ->
                        countNum.text = "0"
                        count = 0

                    })
                .setNegativeButton("negative",
                    DialogInterface.OnClickListener { dialog, id ->
                    }
                )
                .setNeutralButton("neutral",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "neutral 클릭", Toast.LENGTH_SHORT).show()
                    }

                )
            builder.show()
        }

        countBtn.setOnClickListener {
            count++
            countNum.text = count.toString()
        }

        randomBtn.setOnClickListener {

            createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
                false, getString(R.string.app_name), "App notification channel")

            val channelId = "$packageName-${getString(R.string.app_name)}"
            val title = "7주차 과제"
            val content = "랜덤 숫자 띄우기"

            val intent = Intent(this, RandomActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("count", count)
            }

            val pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(this, channelId)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            builder.setContentTitle(title)
            builder.setContentText(content)
            builder.priority = NotificationCompat.PRIORITY_DEFAULT
            builder.setAutoCancel(true)
            builder.setContentIntent(pendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(NOTIFICATION_ID, builder.build())

        }
    }

    private fun createNotificationChannel(
        context: Context, importance: Int, showBadge: Boolean,
        name: String, description: String,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}