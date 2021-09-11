package com.demirli.a50salesreciepts.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.model.PurchasedItem

class MainViewModel(application: Application, var onGetCardId: OnGetCardId): AndroidViewModel(application),
    MainRepository.OnGetCartIdListener {

    private val repository: MainRepository by lazy {
        MainRepository(
            application.applicationContext,
            this
        )
    }

    fun insertItem(item: Item) = repository.insertItem(item)
    fun getAllItems() = repository.getAllItems()

    fun insertCart(cart: Cart) = repository.insertCart(cart)
    fun getAllCarts() = repository.getAllCarts()
    fun deleteAllCarts() = repository.deleteAllCarts()

    fun insertPurchasedItem(purchasedItem: PurchasedItem) = repository.insertPurchasedItem(purchasedItem)
    fun getAllPurchasedItems() = repository.getAllPurchasedItems()
    fun deleteAllPurchasedItems() = repository.deleteAllPurchasedItems()

    override fun getCartId(id: Long) {
        onGetCardId.cardId(id)
    }

    interface OnGetCardId{
        fun cardId(id:Long)
    }

}