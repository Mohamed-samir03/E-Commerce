package com.mosamir.e_commerce.profile.domain.model

data class UpdateProfileRequest(
    val email: String,
    val image: String,
    val name: String,
    val password: String,
    val phone: String
)