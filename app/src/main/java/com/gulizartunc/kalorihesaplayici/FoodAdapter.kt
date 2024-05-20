package com.gulizartunc.kalorihesaplayici

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gulizartunc.kalorihesaplayici.R
import com.gulizartunc.kalorihesaplayici.Food

class FoodAdapter(private val foods: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
        val foodPortion: TextView = itemView.findViewById(R.id.foodPortion)
        val foodCalorie: TextView = itemView.findViewById(R.id.foodCalorie)

        fun bind(food: Food) {
            foodImage.setImageResource(food.imageRes)
            foodName.text = food.name
            foodPortion.text = food.portion
            foodCalorie.text = food.calorie.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int = foods.size
}