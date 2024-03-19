package com.example.architectureassignment

import android.app.Application
import com.example.architectureassignment.di.provideDomainModule
import com.example.architectureassignment.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchitectureAssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArchitectureAssignmentApplication)
            modules(
                provideDomainModule(),
                providePresentationModule()
            )
        }
    }

}