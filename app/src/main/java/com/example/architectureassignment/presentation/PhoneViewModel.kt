package com.example.architectureassignment.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.architectureassignment.domain.entity.PhoneFormat
import com.example.architectureassignment.domain.usecase.FormatPhoneToRuLocaleUseCase
import com.example.architectureassignment.domain.usecase.ValidatePhoneUseCase

class PhoneViewModel(
    private val validatePhoneUseCase: ValidatePhoneUseCase,
    private val formatPhoneToRuLocaleUseCase: FormatPhoneToRuLocaleUseCase
) : ViewModel() {

    private val _phoneState: MutableState<PhoneState> = mutableStateOf(PhoneState())
    val phoneState: State<PhoneState> get() = _phoneState

    private val _formattedPhone: MutableState<String> = mutableStateOf("")
    val formattedPhone: State<String> get() = _formattedPhone

    fun onPhoneChange(phone: String) {
        _phoneState.value = _phoneState.value.copy(
            phone = phone,
            isError = false
        )
    }

    fun onFormatPhone() {
        val phone = _phoneState.value.phone
        val validationResult = validatePhoneUseCase(phone)

        _phoneState.value = _phoneState.value.copy(
            isError = !validationResult.isSuccessful
        )

        _formattedPhone.value = ""

        if (validationResult.isSuccessful) {
            try {
                val formattedPhone = formatPhoneToRuLocaleUseCase(
                    phone = phone,
                    initialPhoneFormat = validationResult.phoneFormat ?: PhoneFormat.NO_TRAILING_DIGIT
                )
                _formattedPhone.value = formattedPhone
            } catch (e: StringIndexOutOfBoundsException) {
                _phoneState.value = _phoneState.value.copy(
                    isError = true
                )
            }
        }
    }

}