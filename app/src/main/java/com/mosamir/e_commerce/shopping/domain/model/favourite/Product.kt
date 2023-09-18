package com.mosamir.e_commerce.shopping.domain.model.favourite

data class Product(
    val description: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val name: String,
    val old_price: Int,
    val price: Int
)