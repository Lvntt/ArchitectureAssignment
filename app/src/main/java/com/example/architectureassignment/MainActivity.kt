package com.example.architectureassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.architectureassignment.presentation.PhonePresenter
import com.example.architectureassignment.presentation.ui.screen.PhoneScreen
import com.example.architectureassignment.presentation.ui.theme.ArchitectureAssignmentTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val phonePresenter: PhonePresenter = get()
            ArchitectureAssignmentTheme {
                PhoneScreen(presenter = phonePresenter).Render()
            }
        }
    }
}