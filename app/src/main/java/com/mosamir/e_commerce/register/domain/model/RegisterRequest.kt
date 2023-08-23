package com.mosamir.e_commerce.register.domain.model

data class RegisterRequest(
    val email: String,
    val image: String,
    val name: String,
    val password: String,
    val phone: String
)