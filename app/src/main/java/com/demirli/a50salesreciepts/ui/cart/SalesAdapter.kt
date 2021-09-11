package com.demirli.a50salesreciepts.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.PurchasedItem
import java.lang.StringBuilder

class SalesAdapter(var purchasedItemList:List<Pair<Int,List<PurchasedItem>>>, var groupedCart: Map<Int, List<Cart>>): RecyclerView.Adapter<SalesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sales_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = purchasedItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cartId.setText("Id: " + purchasedItemList[position].second[0].cartId.toString())

        val items = StringBuilder()
        purchasedItemList[position].second.forEach {
            items.append(it.description + " ")
        }
        holder.items.setText(items)

        holder.price.setText(groupedCart[purchasedItemList[position].second[0].cartId]!![0].price.toString() + " TL")
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var cartId = view.findViewById<TextView>(R.id.cart_id_tv)
        var items = view.findViewById<TextView>(R.id.items_tv)
        var price = view.findViewById<TextView>(R.id.price_tv)
    }
}