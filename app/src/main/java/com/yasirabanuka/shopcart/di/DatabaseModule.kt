package com.yasirabanuka.shopcart.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.yasirabanuka.shopcart.data.local.BuyCartConverters
import com.yasirabanuka.shopcart.data.local.BuyCartDao
import com.yasirabanuka.shopcart.data.local.BuyCartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
        gson: Gson
    ): BuyCartDatabase {
        return Room.databaseBuilder(
            context,
            BuyCartDatabase::class.java,
            "buycartdb"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(BuyCartConverters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesBuyCartDao(buyCartDatabase: BuyCartDatabase): BuyCartDao {
        return buyCartDatabase.getBuyCartDao()
    }
}