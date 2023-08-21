package com.mosamir.e_commerce.login.domain.model

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)