package com.example.architectureassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.architectureassignment.presentation.ui.screen.PhoneScreen
import com.example.architectureassignment.presentation.ui.theme.ArchitectureAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectureAssignmentTheme {
                PhoneScreen()
            }
        }
    }
}