package com.vzudkin.pizzaapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vzudkin.pizzaapp.R
import com.vzudkin.pizzaapp.model.Product

class ProductAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.PizzaViewHolder>() {

    inner class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct = itemView.findViewById<ImageView>(R.id.imageProduct)
        val nameProduct = itemView.findViewById<TextView>(R.id.nameProduct)
        val ingredientsProduct = itemView.findViewById<TextView>(R.id.ingredientsText)
        val priceProduct = itemView.findViewById<TextView>(R.id.priceProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return PizzaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val list = productList[position]
        val image = list.image

        val context: Context

        holder.nameProduct.text = list.name
        holder.ingredientsProduct.text = list.ingredients
        holder.priceProduct.text = list.price

        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(image)
            .into(holder.imgProduct)

    }


    override fun getItemCount(): Int = productList.size

    fun listProducts(list: ArrayList<Product>) {
        this.productList.apply {
            clear()
            addAll(list)
        }
    }

}