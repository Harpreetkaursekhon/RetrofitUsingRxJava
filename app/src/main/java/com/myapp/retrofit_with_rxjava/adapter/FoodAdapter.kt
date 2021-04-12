package com.myapp.retrofit_with_rxjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.retrofit_with_rxjava.R
import com.myapp.retrofit_with_rxjava.model.Food

class FoodAdapter(private var context: Context, private var foodlist: ArrayList<Food>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.name)
        val price: TextView = view.findViewById(R.id.price)
        val image: ImageView = view.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodlist[position]
        holder.name.text = food.name
        holder.price.text = food.price.toString()
        Glide.with(context).load(food.image).into(holder.image)

    }

    override fun getItemCount(): Int = foodlist.size


    fun setData(foodlist: ArrayList<Food>) {
        this.foodlist=foodlist
        notifyDataSetChanged()
    }

}