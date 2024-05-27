package com.gulizartunc.bitkibyme

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.gulizartunc.bitkibyme.R
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var plantImageView: ImageView
    private lateinit var timeTextView: TextView
    private var timeSpent: Long = 0
    private var lastLoginTime: Long = 0

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 60000L // 1 dakika

    private val updateRunnable = object : Runnable {
        override fun run() {
            updatePlantGrowth()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plantImageView = findViewById(R.id.plantImageView)
        timeTextView = findViewById(R.id.timeTextView)

        // Son giriş zamanını ve geçirilen süreyi yükleyin
        val sharedPreferences = getSharedPreferences("PlantPrefs", MODE_PRIVATE)
        lastLoginTime = sharedPreferences.getLong("lastLoginTime", System.currentTimeMillis())
        timeSpent = sharedPreferences.getLong("timeSpent", 0)

        // Bitkiyi güncelle
        updatePlantGrowth()

        // Handler ile bitkiyi periyodik olarak güncelle
        handler.post(updateRunnable)
    }

    private fun updatePlantGrowth() {
        val currentTime = System.currentTimeMillis()
        val timeSinceLastLogin = currentTime - lastLoginTime
        val daysSinceLastLogin = TimeUnit.MILLISECONDS.toDays(timeSinceLastLogin)

        if (daysSinceLastLogin >= 3) {
            // 3 günden fazla süredir giriş yapılmamış, bitkiyi öldür
            plantImageView.setImageResource(R.drawable.ic_plant_dead)
            timeTextView.text = "Bitki öldü. 3 gün üst üste girmedin ."

            // Durumları sıfırla
            val editor = getSharedPreferences("PlantPrefs", MODE_PRIVATE).edit()
            editor.putLong("lastLoginTime", currentTime)
            editor.putLong("timeSpent", 0)
            editor.apply()
        } else {
            // Zaman geçti, bitkiyi büyüt
            timeSpent += timeSinceLastLogin
            lastLoginTime = currentTime

            // Gün, saat ve dakika hesaplamaları
            val daysSpent = TimeUnit.MILLISECONDS.toDays(timeSpent) + 1
            val hoursSpent = TimeUnit.MILLISECONDS.toHours(timeSpent) % 24
            val minutesSpent = TimeUnit.MILLISECONDS.toMinutes(timeSpent) % 60

            // Bitkiyi büyütme mantığı
            when {
                daysSpent < 7 -> plantImageView.setImageResource(R.drawable.ic_plant_small)

                else -> plantImageView.setImageResource(R.drawable.ic_plant_large)
            }

            // Geçirilen zamanı gün, saat ve dakika olarak göster
            timeTextView.text = "Geçirilen zaman: $daysSpent .gün," +
                    " $hoursSpent saat, $minutesSpent dakika"

            // Durumları kaydet
            val editor = getSharedPreferences("PlantPrefs", MODE_PRIVATE).edit()
            editor.putLong("lastLoginTime", lastLoginTime)
            editor.putLong("timeSpent", timeSpent)
            editor.apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)
    }
}
