package com.vzudkin.pizzaapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vzudkin.pizzaapp.R
import com.vzudkin.pizzaapp.model.Tab
import com.vzudkin.pizzaapp.model.Type

class TabAdapter(
    private val list: ArrayList<Tab>,
    private val callbackElementChecked: (Boolean, TextView, CardView, String?) -> Unit,
) : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {

    private var elementChecked: Int? = null
    private var elementCheckedOld: Int? = null
    private var currentType: String? = null

    inner class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView? = null
        var textView: TextView? = null

        init {
            cardView = itemView.findViewById(R.id.itemCardView)
            textView = itemView.findViewById(R.id.itemTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tab_list, parent, false)
        return TabViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.textView?.text = list[holder.adapterPosition].text

        holder.cardView?.setOnClickListener {
            elementChecked?.let {
                elementCheckedOld = it

            }
            elementChecked = holder.adapterPosition
            currentType = list[holder.adapterPosition].text

            if (elementCheckedOld == null) {
                elementCheckedOld = elementChecked
            }
            redrawing()

        }

        elementChecked?.let {
            if (holder.textView != null &&
                holder.cardView != null
            ) {
                val cellActive = it == holder.adapterPosition
                callbackElementChecked.invoke(
                    cellActive,
                    holder.textView!!,
                    holder.cardView!!,
                    if (cellActive) currentType else null
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size

    private fun redrawing() {
        notifyItemChanged(elementChecked!!)
        notifyItemChanged(elementCheckedOld!!)
    }

}