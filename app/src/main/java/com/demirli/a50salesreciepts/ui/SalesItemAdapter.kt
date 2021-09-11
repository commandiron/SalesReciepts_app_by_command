package com.demirli.a50salesreciepts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Item

class SalesItemAdapter(var cartItemList: List<Item>): RecyclerView.Adapter<SalesItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartitem_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  cartItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stockItemIdTextView.setText(cartItemList[position].stockItemId.toString())
        holder.descriptionTextView.setText(cartItemList[position].description)
        holder.unitPriceTextView.setText(cartItemList[position].unitPrice.toString() + "TL")
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val stockItemIdTextView = view.findViewById<TextView>(R.id.stock_item_id_tv)
        val descriptionTextView = view.findViewById<TextView>(R.id.description_tv)
        val unitPriceTextView = view.findViewById<TextView>(R.id.unit_price_tv)
    }

}