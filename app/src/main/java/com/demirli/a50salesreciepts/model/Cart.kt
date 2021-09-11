package com.demirli.a50salesreciepts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class Cart(

    @PrimaryKey(autoGenerate = true)
    var cartId: Int = 0,
    var userId: Int,
    var price: Float
) {
}