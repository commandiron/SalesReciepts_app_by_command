package com.demirli.a50salesreciepts.localdata

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demirli.a50salesreciepts.model.Cart
import com.demirli.a50salesreciepts.model.Item
import com.demirli.a50salesreciepts.model.PurchasedItem

@Database(entities = [Item::class, Cart::class, PurchasedItem::class], version = 1)
abstract class SalesDatabase: RoomDatabase() {

    abstract fun salesDao(): SalesDao

    companion object{
        @Volatile
        var INSTANCE: SalesDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SalesDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SalesDatabase::class.java,
                    "sales.db")
                    .addCallback(roomDbCallback)
                    .build()
            }
            return INSTANCE as SalesDatabase
        }

        private val roomDbCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE).execute()
            }
        }

        class PopulateAsyncTask(private val db: SalesDatabase?): AsyncTask<Void, Void, Void>(){
            private val dao: SalesDao? by lazy { db?.salesDao() }
            override fun doInBackground(vararg params: Void?): Void? {

                var item = Item(description = "Elma", unitPrice = 5.00f)
                dao?.insertItem(item)

                item = Item(description = "Armut", unitPrice = 6.00f)
                dao?.insertItem(item)

                item = Item(description = "Muz", unitPrice = 10.00f)
                dao?.insertItem(item)

                return null
            }
        }
    }

}