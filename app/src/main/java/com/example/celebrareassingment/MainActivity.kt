package com.example.celebrareassingment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.celebrareassingment.ui.theme.CelebrareAssingmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CelebrareAssingmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Editor_Screen(modifier = Modifier.padding(innerPadding), viewModel = MainViewModel())
                }
            }
        }
    }
}

