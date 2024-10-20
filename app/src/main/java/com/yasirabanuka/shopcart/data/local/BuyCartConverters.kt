package com.yasirabanuka.shopcart.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yasirabanuka.shopcart.data.remote.dto.request.Address
import com.yasirabanuka.shopcart.data.remote.dto.request.Name
import com.yasirabanuka.shopcart.domain.model.PaymentInfo
import javax.inject.Inject


@ProvidedTypeConverter
class BuyCartConverters @Inject constructor(
    private val gson: Gson
) {

    @TypeConverter
    fun fromName(name: Name): String {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toName(json: String): Name {
        val type = object : TypeToken<Name>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromAddress(address: Address): String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(json: String): Address {
        val type = object : TypeToken<Address>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPaymentInfo(paymentInfo: PaymentInfo?): String {
        return gson.toJson(paymentInfo)
    }

    @TypeConverter
    fun toPaymentInfo(json: String): PaymentInfo? {
        val type = object : TypeToken<PaymentInfo?>() {}.type
        return gson.fromJson(json, type)
    }
}