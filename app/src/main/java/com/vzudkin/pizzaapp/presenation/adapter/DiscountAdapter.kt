package com.vzudkin.pizzaapp.presenation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vzudkin.pizzaapp.R
import com.vzudkin.pizzaapp.data.model.Discount

class DiscountAdapter : RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>() {
    private val discountList = ArrayList<Discount>()

    inner class DiscountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imgDiscount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.discount_item, parent, false)
        return DiscountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(discountList[holder.adapterPosition].image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = discountList.size

    fun getDiscounts(list: ArrayList<Discount>) {
        this.discountList.apply {
            clear()
            addAll(list)
        }
    }
}