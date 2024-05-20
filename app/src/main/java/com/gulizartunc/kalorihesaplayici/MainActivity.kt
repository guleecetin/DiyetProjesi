package com.gulizartunc.kalorihesaplayici
import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gulizartunc.kalorihesaplayici.FoodAdapter
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper.Companion.COLUMN_CALORIE
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper.Companion.COLUMN_IMAGE
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper.Companion.COLUMN_NAME
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper.Companion.COLUMN_PORTION
import com.gulizartunc.kalorihesaplayici.FoodDatabaseHelper.Companion.TABLE_NAME
import com.gulizartunc.kalorihesaplayici.databinding.ActivityMainBinding
import com.gulizartunc.kalorihesaplayici.Food

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Database oluştur
        val dbHelper = FoodDatabaseHelper(this)

        // Örnek yiyecekler ekle
        dbHelper.writableDatabase.use { db ->
            val foods = listOf(
                Food("Elma", R.drawable.apple, "1 adet", 52),
                Food("Muz", R.drawable.banana, "1 adet", 105),
                Food("Hamburger", R.drawable.burger, "1 porsiyon", 295),
                Food("Çikolata", R.drawable.chocolate, "100g", 546),
                Food("Pirinç", R.drawable.rice, "100g", 130)
            )

            foods.forEach { food ->
                val contentValues = ContentValues().apply {
                    put(COLUMN_NAME, food.name)
                    put(COLUMN_IMAGE, food.imageRes)
                    put(COLUMN_PORTION, food.portion)
                    put(COLUMN_CALORIE, food.calorie)
                }
                db.insert(TABLE_NAME, null, contentValues)
            }
        }

        // Veritabanından yiyecekleri al
        val foodList: ArrayList<Food> = dbHelper.readableDatabase.use { db ->
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            val list = ArrayList<Food>()
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val imageRes = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE))
                val portion = cursor.getString(cursor.getColumnIndex(COLUMN_PORTION))
                val calorie = cursor.getInt(cursor.getColumnIndex(COLUMN_CALORIE))
                list.add(Food(name, imageRes, portion, calorie))
            }
            cursor.close()
            list
        }

        // RecyclerView ve Adapter ayarları
        foodAdapter = FoodAdapter(foodList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = foodAdapter
    }
}