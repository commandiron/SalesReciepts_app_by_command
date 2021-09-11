package com.demirli.a50salesreciepts.ui.cart

import android.hardware.camera2.TotalCaptureResult
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a50salesreciepts.R
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.model.PurchasedItem
import com.demirli.a50salesreciepts.ui.CheckoutItemListAdapter
import com.demirli.a50salesreciepts.ui.ItemListAdapter
import com.demirli.a50salesreciepts.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class CartActivity : AppCompatActivity(),
    MainViewModel.OnGetCardId{

    private lateinit var viewModel: MainViewModel

    private lateinit var listOfItemFromMainActivity: ArrayList<Item>

    private var totalPrice: Float? = null

    private lateinit var checkoutItemListAdapter: CheckoutItemListAdapter

    private var cartId: Long? = null

    private lateinit var groupedCart: Map<Int, List<Cart>>

    private lateinit var salesAdapter: SalesAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setUi()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUi(){
        viewModel = MainViewModel(application, this)

        getAllCarts()

        getIntentFromMainActivity()

        checkout_btn.setOnClickListener {
            checkoutProcess()
        }

        cancel_btn.setOnClickListener {
            cancelProcess()
        }

        get_all_btn.setOnClickListener {
            getAllProcess()
        }

        clear_all_btn.setOnClickListener {
            clearAllProcess()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getAllCarts(){
        //Burada cartları userId'ye göre getirebiliriz. Şu an gerekli olduğu için query'e eklemedim.
        viewModel.getAllCarts().observe(this, Observer {
            groupedCart = it.stream().collect(Collectors.groupingBy (Cart::cartId))

            totalPrice = null
        })
    }

    private fun getIntentFromMainActivity(){
        intent.extras.let {
            listOfItemFromMainActivity = it!!.getSerializable("CartItems") as ArrayList<Item>
            totalPrice = it!!.getFloat("TotalPrice")

            println(totalPrice!!)

            val cart = Cart(userId = 1, price = totalPrice!!)
            viewModel.insertCart(cart)

            setCheckoutItemListAdapter()
        }
    }

    private fun checkoutProcess(){
        for(i in listOfItemFromMainActivity){
            val purchasedItem = PurchasedItem(purshasedItemId = i.stockItemId,description = i.description, cartId = cartId!!.toInt())
            viewModel.insertPurchasedItem(purchasedItem)
        }

        Toast.makeText(this, "Your Checkout is successful.", Toast.LENGTH_SHORT).show()
        listOfItemFromMainActivity.clear()
        checkoutItemListAdapter.notifyDataSetChanged()
    }
    private fun setCheckoutItemListAdapter(){
        cart_recyclerView.layoutManager = LinearLayoutManager(this)
        checkoutItemListAdapter = CheckoutItemListAdapter(listOfItemFromMainActivity)
        cart_recyclerView.adapter = checkoutItemListAdapter
    }

    private fun cancelProcess(){
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getAllProcess(){
        //Burda purchasedItems'laarı userId'ye göre getirebiliriz. Şu an gerekli olduğu için query'e eklemedim.
        viewModel.getAllPurchasedItems().observe(this, Observer {

            val groupedPurchasedItem = it.stream().collect(Collectors.groupingBy(PurchasedItem::cartId))

            val groupedPurchasedItemList = groupedPurchasedItem.toList()

            setSalesAdapter(groupedPurchasedItemList)
        })
    }
    private fun setSalesAdapter(groupedPurchasedItemList: List<Pair<Int, List<PurchasedItem>>>){
        all_sales_recyclerView.layoutManager = LinearLayoutManager(this)
        salesAdapter = SalesAdapter(groupedPurchasedItemList, groupedCart)
        all_sales_recyclerView.adapter = salesAdapter
    }

    private fun clearAllProcess(){
        viewModel.deleteAllCarts()
        viewModel.deleteAllPurchasedItems()
    }

    override fun cardId(id: Long) {
        cartId = id
    }
}
