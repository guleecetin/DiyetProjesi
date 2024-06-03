package com.nurhayatdere.desteksayfasi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nurhayatdere.desteksayfasi.databinding.ActivityMainBinding
import com.nurhayatdere.desteksayfasi.databinding.ActivitySporKocuDestekSayfasiBinding

class SporKocuDestekSayfasi : AppCompatActivity() {
    private lateinit var binding: ActivitySporKocuDestekSayfasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySporKocuDestekSayfasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}