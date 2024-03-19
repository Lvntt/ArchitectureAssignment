package com.example.architectureassignment.di

import com.example.architectureassignment.domain.usecase.FormatPhoneToRuLocaleUseCase
import com.example.architectureassignment.domain.usecase.ValidatePhoneUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomainModule(): Module = module {

    factory { ValidatePhoneUseCase() }

    factory { FormatPhoneToRuLocaleUseCase() }

}