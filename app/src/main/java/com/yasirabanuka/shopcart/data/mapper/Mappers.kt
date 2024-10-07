package com.yasirabanuka.shopcart.data.mapper

import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product
import com.yasirabanuka.shopcart.domain.model.ProductInCart
import com.yasirabanuka.shopcart.domain.model.UserProfile

/**
 * @created 12/07/2022 - 4:59 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

fun ProfileDto.toUserProfile(): UserProfile {
    return UserProfile(
        email = email,
        password = password,
        username = username,
        name = name,
        address = address,
        phone = phone,
    )
}

fun ProductsDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        image = image,
        price = price,
        quantity = 1
    )
}

fun Product.toProductInCart(): ProductInCart {
    return ProductInCart(
        id = id,
        title = title,
        image = image,
        price = price,
        quantity = 1,
        pricePerItem = price.toDouble()
    )
}

fun ProductInCart.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        image = image,
        price = price,
        quantity = quantity
    )
}

fun ProductsDto.toProductInCart(): ProductInCart {
    return ProductInCart(
        id = id,
        title = title,
        image = image,
        price = price,
        quantity = 1,
        pricePerItem = price.toDouble()
    )
}