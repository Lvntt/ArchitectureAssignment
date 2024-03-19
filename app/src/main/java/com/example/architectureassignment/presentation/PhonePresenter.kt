package com.example.architectureassignment.presentation

import com.example.architectureassignment.domain.entity.PhoneFormat
import com.example.architectureassignment.domain.usecase.FormatPhoneToRuLocaleUseCase
import com.example.architectureassignment.domain.usecase.ValidatePhoneUseCase
import com.example.architectureassignment.presentation.ui.screen.IPhoneScreen

class PhonePresenter(
    private val validatePhoneUseCase: ValidatePhoneUseCase,
    private val formatPhoneToRuLocaleUseCase: FormatPhoneToRuLocaleUseCase
) {
    var phoneScreen: IPhoneScreen? = null

    fun onFormatPhone(phone: String) {
        val validationResult = validatePhoneUseCase(phone)
        if (!validationResult.isSuccessful) {
            phoneScreen?.showPhoneError()
        }

        phoneScreen?.setFormattedPhone("")

        if (validationResult.isSuccessful) {
            try {
                val formattedPhone = formatPhoneToRuLocaleUseCase(
                    phone = phone,
                    initialPhoneFormat = validationResult.phoneFormat ?: PhoneFormat.NO_TRAILING_DIGIT
                )
                phoneScreen?.hidePhoneError()
                phoneScreen?.setFormattedPhone(formattedPhone)
            } catch (e: StringIndexOutOfBoundsException) {
                phoneScreen?.showPhoneError()
            }
        }

    }

}