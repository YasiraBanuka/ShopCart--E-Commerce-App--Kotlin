package com.yasirabanuka.shopcart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart")
data class ProductInCart(
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: String,
    val title: String,
    val quantity: Int,
    val pricePerItem: Double,
)
