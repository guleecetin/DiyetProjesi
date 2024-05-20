package com.nurhayatdere.kcalsayfasi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nurhayatdere.kcalsayfasi.databinding.ActivityMainBinding


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

        binding.HESAPLAbutton.setOnClickListener {
            val yas = binding.YaseditText.text.toString().toIntOrNull()
            val kilo = binding.KiloeditText.text.toString().toDoubleOrNull()
            val boy = binding.BoyeditText.text.toString().toDoubleOrNull()
            val cinsiyet = when {
                binding.kadinradioButton.isChecked -> "KADIN"
                binding.erkekradioButton.isChecked -> "ERKEK"
                else -> null
            }
        // deneme 1 2
            if (yas == null || kilo == null || boy == null || cinsiyet == null) {
                Toast.makeText(this, "Lütfen tüm bilgileri eksiksiz giriniz", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val bmr = hesaplaBMR(cinsiyet, yas, kilo, boy)
            val kaloriIhtiyaci = kaloriHesaplama(bmr)
            Toast.makeText(this, "Günlük Kalori İhtiyacı: %.2f kcal".format(kaloriIhtiyaci), Toast.LENGTH_LONG).show()

        }
    }

    private fun hesaplaBMR(cinsiyet: String, yas: Int, kilo: Double, boy: Double): Double {
        return if (cinsiyet == "ERKEK") {
            88.362 + (13.397 * kilo) + (4.799 * boy) - (5.677 * yas)
        }
        else {
            447.593 + (9.247 * kilo) + (3.098 * boy) - (4.330 * yas)
        }
    }

    private fun kaloriHesaplama(bmr: Double): Double {
        // Sabit bir aktivite düzeyi ile örnek hesaplama (örneğin, orta düzeyde aktif)
        return bmr * 1.55
    }
}




