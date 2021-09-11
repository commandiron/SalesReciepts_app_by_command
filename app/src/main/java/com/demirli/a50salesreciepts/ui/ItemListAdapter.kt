package com.demirli.a50salesreciepts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Item

class ItemListAdapter(var itemList: List<Item>, var onCartImageClickListener: OnCartImageClickListener): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stockItemIdTextView.setText(itemList[position].stockItemId.toString())
        holder.descriptionTextView.setText(itemList[position].description)
        holder.unitPriceTextView.setText(itemList[position].unitPrice.toString() + "TL")

        holder.addCartImageView.setOnClickListener {
            onCartImageClickListener.onAddCartClick(itemList[position])
        }

        holder.addCartTextView.setOnClickListener {
            onCartImageClickListener.onAddCartClick(itemList[position])
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val stockItemIdTextView = view.findViewById<TextView>(R.id.stock_item_id_tv)
        val descriptionTextView = view.findViewById<TextView>(R.id.description_tv)
        val unitPriceTextView = view.findViewById<TextView>(R.id.unit_price_tv)
        val addCartImageView = view.findViewById<ImageView>(R.id.add_cart_iv)
        val addCartTextView = view.findViewById<TextView>(R.id.add_cart_tv)
    }

    interface OnCartImageClickListener{
        fun onAddCartClick(item: Item)
    }
}