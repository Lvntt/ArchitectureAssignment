package com.example.architectureassignment.presentation.ui.screen

interface IPhoneScreen {
    fun showPhoneError()
    fun hidePhoneError()
    fun setFormattedPhone(formattedPhone: String)
}