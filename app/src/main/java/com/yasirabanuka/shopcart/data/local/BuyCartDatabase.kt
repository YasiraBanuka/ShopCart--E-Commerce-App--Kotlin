package com.yasirabanuka.shopcart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yasirabanuka.shopcart.domain.model.UserProfile
import com.yasirabanuka.shopcart.domain.model.Product
import com.yasirabanuka.shopcart.domain.model.ProductInCart



@Database(
    entities = [
        UserProfile::class,
        Product::class,
        ProductInCart::class
    ],
    exportSchema = false,
    version = 7
)
@TypeConverters(BuyCartConverters::class)
abstract class BuyCartDatabase : RoomDatabase() {

    abstract fun getBuyCartDao(): BuyCartDao
}