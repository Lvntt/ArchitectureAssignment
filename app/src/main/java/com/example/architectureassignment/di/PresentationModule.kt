package com.example.architectureassignment.di

import com.example.architectureassignment.presentation.PhonePresenter
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module = module {

    factory { PhonePresenter(get(), get()) }

}