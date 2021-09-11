package com.demirli.a50salesreciepts.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.ui.cart.CartActivity
import com.demirli.a50salesreciepts.ui.ItemListAdapter
import com.demirli.a50salesreciepts.ui.SalesItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    ItemListAdapter.OnCartImageClickListener,
    MainViewModel.OnGetCardId {

    private lateinit var viewModel: MainViewModel

    private lateinit var itemListAdapter: ItemListAdapter

    private lateinit var salesItemAdapter: SalesItemAdapter

    private var cartItems = ArrayList<Item>()

    private var totalPrice = 0.00f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
    }

    private fun setUi(){

        viewModel = MainViewModel(application, this)

        getAllItems()

        setSalesItemAdapter()

        go_to_cart_btn.setOnClickListener {
            goToCartProcess()
        }

        clear_btn.setOnClickListener {
            clearProcess()
        }

        setTotalTextView()
    }

    private fun getAllItems(){
        viewModel.getAllItems().observe(this, Observer {
            setItemListAdapter(it)
        })
    }
    private fun setItemListAdapter(list: List<Item>){
        itemList_recyclerView.layoutManager = LinearLayoutManager(this)
        itemListAdapter = ItemListAdapter(list, this)
        itemList_recyclerView.adapter = itemListAdapter
    }

    private fun setSalesItemAdapter(){
        cartItemList_recyclerView.layoutManager = LinearLayoutManager(this)
        salesItemAdapter = SalesItemAdapter(cartItems)
        cartItemList_recyclerView.adapter = salesItemAdapter
    }

    private fun goToCartProcess(){
        if(cartItems.size != 0){
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("CartItems", cartItems)
            intent.putExtra("TotalPrice", totalPrice)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Your Cart is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearProcess(){
        cartItems.clear()
        totalPrice = 0f
        salesItemAdapter.notifyDataSetChanged()
        total_tv.setText(totalPrice.toString() + "TL")
    }

    private fun setTotalTextView(){
        total_tv.setText(totalPrice.toString() + "TL")
    }

    override fun onAddCartClick(item: Item) {
        cartItems.add(item)
        salesItemAdapter.notifyDataSetChanged()
        totalPrice += item.unitPrice
        total_tv.setText(totalPrice.toString() + "TL")
    }

    override fun cardId(id: Long) {
    }
}
