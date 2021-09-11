package com.demirli.a50salesreciepts.localdata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.model.PurchasedItem

@Dao
interface SalesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item?)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE stockItemId= :itemId")
    fun getSingleItem(itemId: Int?): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(cart: Cart?): Long

    @Query("SELECT * FROM carts")
    fun getAllCarts(): LiveData<List<Cart>>

    @Query("SELECT * FROM carts WHERE cartId= :cartId")
    fun getSingleCart(cartId: Int?): LiveData<Cart>

    @Query("DELETE FROM carts")
    fun deleteAllCarts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchasedItem(purchasedItem: PurchasedItem?)

    @Query("SELECT * FROM purchasedItems")
    fun getAllPurchasedItems(): LiveData<List<PurchasedItem>>

    @Query("DELETE FROM purchasedItems")
    fun deleteAllPurchasedItems()



}
