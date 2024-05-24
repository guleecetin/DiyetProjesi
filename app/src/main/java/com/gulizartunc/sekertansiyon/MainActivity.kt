package com.gulizartunc.sekertansiyon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gulizartunc.sekertansiyon.databinding.ActivityMainBinding


//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var gidaList: MutableList<Gida>
    private lateinit var gidaAdapter: GidaAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var database = FirebaseDatabase.getInstance()
        val reference = database.getReference("data") // Replace "data" with your desired path

        // Firebase bağlantısını kur
        database = FirebaseDatabase.getInstance()

        // Gıda listesini başlat
        gidaList = mutableListOf()

        // Gıda veritabanına veri ekleme butonu (İsteğe bağlı)
       /* buttonEkle.setOnClickListener {
            val isim = editTextIsim.text.toString()
            val kanSekeriEtki = editTextKanSekeriEtki.text.toString()
            val tansiyonEtki = editTextTansiyonEtki.text.toString()
            val tuketimOnerisi = editTextTuketimOnerisi.text.toString()

            val gida = Gida(isim, kanSekeriEtki, tansiyonEtki, tuketimOnerisi)
            val gidaRef = database.getReference("Gıdalar")
            gidaRef.push().setValue(gida)
        }

        // Gıda önerilerini gösterecek RecyclerView'u kur
        recyclerViewGidaOnerileri.layoutManager = LinearLayoutManager(this)
        gidaAdapter = GidaAdapter(gidaList)
        recyclerViewGidaOnerileri.adapter = gidaAdapter*/

        // Gıda veritabanından veri alma ve listeye ekleme
        val gidaRef = database.getReference("Gıdalar")
        val gidaListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                gidaList.clear()
                for (dataSnapshot in dataSnapshot.children) {
                    val gida = dataSnapshot.getValue(Gida::class.java)
                    if (gida != null) {
                        gidaList.add(gida)
                    }
                }

                // Gıda listesini RecyclerView'a güncelle
                gidaAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Hata işlemesi
            }
        }

        gidaRef.addValueEventListener(gidaListener)
    }
}
