package com.demirli.a50salesreciepts.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "items")
data class Item(

    @PrimaryKey(autoGenerate = true)
    var stockItemId: Int = 0,
    var description: String,
    var unitPrice: Float

): Serializable {
}