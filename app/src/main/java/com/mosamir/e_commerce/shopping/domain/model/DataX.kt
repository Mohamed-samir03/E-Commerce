package com.mosamir.e_commerce.shopping.domain.model

data class DataX(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val images: List<String>,
    val in_cart: Boolean,
    val in_favorites: Boolean,
    val name: String,
    val old_price: Double,
    val price: Double
)