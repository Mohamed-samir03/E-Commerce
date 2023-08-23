package com.mosamir.e_commerce.register.domain.model

data class RegisterResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)