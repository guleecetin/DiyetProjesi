package com.nurhayatdere.desteksayfasi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nurhayatdere.desteksayfasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.button.setOnClickListener {
            val diyetisyen = Intent(applicationContext , DiyetisyenDesteksayfasi::class.java)
            startActivity(diyetisyen)
        }

        binding.button3.setOnClickListener {
            val psikoloji = Intent(applicationContext , PsikolojikDestekSayfasi::class.java)
            startActivity(psikoloji)
        }

        binding.button2.setOnClickListener {
            val spor = Intent(applicationContext , SporKocuDestekSayfasi::class.java)
            startActivity(spor)
        }

        binding.button4.setOnClickListener {
            val ilanlar = Intent(applicationContext , ilanVerSayfasi::class.java)
            startActivity(ilanlar)
        }

    }


}