package com.mosamir.e_commerce.shopping.domain.model

data class ProductResponse(
    val `data`: Data,
    val message: Any,
    val status: Boolean
)