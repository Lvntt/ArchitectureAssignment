package com.example.architectureassignment.domain.usecase

import com.example.architectureassignment.domain.entity.PhoneFormat
import com.example.architectureassignment.domain.entity.PhoneValidationResult

class ValidatePhoneUseCase {

    operator fun invoke(phone: String): PhoneValidationResult {
        if (phone.length < MIN_LENGTH || phone.length > MAX_LENGTH) {
            return PhoneValidationResult(
                isSuccessful = false
            )
        }

        if (phone.any { !it.isDigit() } ) {
            return PhoneValidationResult(
                isSuccessful = false
            )
        }

        if (phone.length == MAX_LENGTH) {
            if (phone.first() !in VALID_FIRST_DIGITS) {
                return PhoneValidationResult(
                    isSuccessful = false
                )
            }
            return PhoneValidationResult(
                isSuccessful = true,
                phoneFormat = PhoneFormat.WITH_TRAILING_DIGIT
            )
        } else {
            return PhoneValidationResult(
                isSuccessful = true,
                phoneFormat = PhoneFormat.NO_TRAILING_DIGIT
            )
        }
    }

    private companion object {
        const val MIN_LENGTH = 10
        const val MAX_LENGTH = 11

        val VALID_FIRST_DIGITS = listOf('7', '8')
    }

}