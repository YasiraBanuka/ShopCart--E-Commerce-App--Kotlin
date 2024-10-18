package com.yasirabanuka.shopcart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wishlist")
data class Product(
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: String,
    val title: String,
    val quantity: Int
) {
    fun formatPrice(): String {
        return String.format("%.2f", price.toDouble())
    }
}
