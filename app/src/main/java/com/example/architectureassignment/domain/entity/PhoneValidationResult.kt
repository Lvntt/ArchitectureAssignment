package com.example.architectureassignment.domain.entity

data class PhoneValidationResult(
    val isSuccessful: Boolean = false,
    val phoneFormat: PhoneFormat? = null
)
