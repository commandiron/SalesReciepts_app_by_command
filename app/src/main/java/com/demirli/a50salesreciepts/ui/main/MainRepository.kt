package com.demirli.a50salesreciepts.ui.main

import android.content.Context
import android.os.AsyncTask
import com.demirli.a50salesreciepts.localdata.SalesDao
import com.demirli.a50salesreciepts.localdata.SalesDatabase
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.model.PurchasedItem

class MainRepository(context: Context, var onGetCartIdListener: OnGetCartIdListener) {

    private val db: SalesDatabase by lazy { SalesDatabase.getInstance(context) }
    private val dao: SalesDao by lazy { db.salesDao() }

    fun insertItem(item: Item) = RepoItemAsyncTask(
        dao
    ).execute(item)
    fun getAllItems() = dao.getAllItems()
    fun getSingleItem(itemId: Int) = dao.getSingleItem(itemId)

    fun insertCart(cart: Cart) = RepoCartAsyncTask(dao).execute(cart)
    fun getAllCarts() = dao.getAllCarts()
    fun getSingleCart(cartId: Int) = dao.getSingleCart(cartId)
    fun deleteAllCarts() = RepoDeleteCartAsyncTask(dao).execute()

    fun insertPurchasedItem(purchasedItem: PurchasedItem) = RepoPurchasedItemAsyncTask(dao).execute(purchasedItem)
    fun getAllPurchasedItems() = dao.getAllPurchasedItems()
    fun deleteAllPurchasedItems() = RepoDeletePurchasedItemAsyncTask(dao).execute()


    private class RepoItemAsyncTask(val dao: SalesDao): AsyncTask<Item, Void, Void>() {
        override fun doInBackground(vararg params: Item?): Void? {
            dao.insertItem(params[0])
            return null
        }
    }

    inner private class RepoCartAsyncTask(val dao: SalesDao): AsyncTask<Cart, Void, Long>() {
        override fun doInBackground(vararg params: Cart?): Long? {
            val result = dao.insertCart(params[0])
            return result
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            onGetCartIdListener.getCartId(result!!)
        }
    }

    inner private class RepoDeleteCartAsyncTask(val dao: SalesDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            dao.deleteAllCarts()
            return null
        }
    }

    private class RepoPurchasedItemAsyncTask(val dao: SalesDao): AsyncTask<PurchasedItem, Void, Void>() {
        override fun doInBackground(vararg params: PurchasedItem?): Void? {
            dao.insertPurchasedItem(params[0])
            return null
        }
    }

    private class RepoDeletePurchasedItemAsyncTask(val dao: SalesDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            dao.deleteAllPurchasedItems()
            return null
        }
    }

    interface OnGetCartIdListener{
        fun getCartId(id: Long)
    }
}