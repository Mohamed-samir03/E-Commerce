package com.mosamir.e_commerce.profile.domain.model

data class ProfileResponse(
    val `data`: Data,
    val message: String?,
    val status: Boolean
)