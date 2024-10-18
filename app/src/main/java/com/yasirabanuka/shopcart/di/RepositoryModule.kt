package com.yasirabanuka.shopcart.di

import com.yasirabanuka.shopcart.data.repository.*
import com.yasirabanuka.shopcart.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsProductRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsWishListRepository(wishListRepositoryImpl: WishListRepositoryImpl): WishListRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}