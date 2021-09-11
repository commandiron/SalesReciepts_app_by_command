package com.demirli.a50salesreciepts.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.ui.ItemListAdapter

class CartItemListAdapter(var itemList: List<Item>): RecyclerView.Adapter<CartItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}