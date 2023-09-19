package com.mosamir.e_commerce.shopping.domain.model.adddelete

data class Product(
    val discount: Int,
    val id: Int,
    val image: String,
    val old_price: Int,
    val price: Int
)