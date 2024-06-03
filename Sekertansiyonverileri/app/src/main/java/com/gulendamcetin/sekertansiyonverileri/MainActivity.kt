//package com.example.healthtracker

package com.gulendamcetin.sekertansiyonverileri

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvGlucose: TextView
    private lateinit var etGlucose: EditText
    private lateinit var tvBloodPressure: TextView
    private lateinit var etSystolic: EditText
    private lateinit var etDiastolic: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvGlucose = findViewById(R.id.tvGlucose)
        etGlucose = findViewById(R.id.etGlucose)
        tvBloodPressure = findViewById(R.id.tvBloodPressure)
        etSystolic = findViewById(R.id.etSystolic)
        etDiastolic = findViewById(R.id.etDiastolic)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val glucoseLevel = etGlucose.text.toString()
            val systolic = etSystolic.text.toString()
            val diastolic = etDiastolic.text.toString()

            if (glucoseLevel.isEmpty() || systolic.isEmpty() || diastolic.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            } else {
                saveData(glucoseLevel, systolic, diastolic)
            }
        }
    }

    private fun saveData(glucoseLevel: String, systolic: String, diastolic: String) {
        Toast.makeText(this, "Veriler kaydedildi:\nŞeker Seviyesi: $glucoseLevel\nSistolik: $systolic\nDiastolik: $diastolic", Toast.LENGTH_LONG).show()
    }
}