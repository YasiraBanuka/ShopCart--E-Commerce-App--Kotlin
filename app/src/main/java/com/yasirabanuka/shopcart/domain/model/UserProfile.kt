package com.yasirabanuka.shopcart.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yasirabanuka.shopcart.data.remote.dto.request.Address
import com.yasirabanuka.shopcart.data.remote.dto.request.Name
import kotlinx.parcelize.Parcelize


@Entity(tableName = "user_profile")
@Parcelize
data class UserProfile(
    @PrimaryKey
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val imageUrl: String = "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/profile.jpg",
    val name: Name,
    val address: Address,
    val paymentInfo: PaymentInfo? = null,
): Parcelable

@Parcelize
data class PaymentInfo(
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpiryDate: String,
    val cardCVV: String
): Parcelable
