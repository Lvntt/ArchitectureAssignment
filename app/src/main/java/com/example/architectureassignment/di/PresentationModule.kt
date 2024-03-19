package com.example.architectureassignment.di

import com.example.architectureassignment.presentation.PhoneViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    viewModel { PhoneViewModel(get(), get()) }

}