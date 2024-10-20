package com.yasirabanuka.shopcart.domain.model

import com.yasirabanuka.shopcart.R

data class OnBoardingItem(
    val image: Int,
    val title: Int,
    val information: Int
) {
    companion object {
        fun provideOnBoardingData(): List<OnBoardingItem> {
            return listOf(
                OnBoardingItem(
                    image = R.drawable.ic_onboarding_product,
                    title = R.string.title1,
                    information = R.string.info1
                ),
                OnBoardingItem(
                    image = R.drawable.ic_onboarding_no_ads,
                    title = R.string.title2,
                    information = R.string.info2
                ),
                OnBoardingItem(
                    image = R.drawable.ic_onboarding_payment,
                    title = R.string.title3,
                    information = R.string.info3
                )
            )
        }
    }
}

