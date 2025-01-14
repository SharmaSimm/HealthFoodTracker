package com.example.bitfit

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.humcomp.bitfitpart2.data.Model

class RVAdapter(private val itemList: MutableList<Model>): RecyclerView.Adapter<RVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // holds list items
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = itemList[position]
        Log.d("position", position.toString())

        holder.itemName.text = model.foodName
        holder. calorieCount.text = model.calorieCount.toString()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val itemName: TextView = itemView.findViewById(R.id.recycler_food_name_view)
        val calorieCount: TextView = itemView.findViewById(R.id.recycler_calorie_view)
    }
}