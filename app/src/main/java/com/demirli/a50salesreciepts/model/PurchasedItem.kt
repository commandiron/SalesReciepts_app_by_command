package com.demirli.a50salesreciepts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchasedItems")
data class PurchasedItem(

    @PrimaryKey(autoGenerate = true)
    var pid: Int = 0,
    var purshasedItemId: Int,
    var description: String,
    var cartId: Int
) {
}