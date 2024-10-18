package com.yasirabanuka.shopcart.data.repository

import com.yasirabanuka.shopcart.data.local.BuyCartDao
import com.yasirabanuka.shopcart.data.remote.BuyCartApi
import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.domain.model.UserProfile
import com.yasirabanuka.shopcart.domain.repository.ProfileRepository
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi,
    private val buyCartDao: BuyCartDao
): ProfileRepository {

    override suspend fun fetchUserProfile(userId: Int): ProfileDto {
        return buyCartApi.user(userId)
    }

    override suspend fun saveUserProfile(userProfile: UserProfile) {
        return buyCartDao.saveUserProfile(userProfile)
    }

    override suspend fun getUserProfile(): UserProfile {
        return buyCartDao.getUserProfile()
    }

    override suspend fun updateUserProfile(userProfile: UserProfile) {
        return buyCartDao.updateUserProfile(userProfile)
    }
}