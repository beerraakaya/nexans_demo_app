package com.berrakaya.mobildemoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.berrakaya.mobildemoapp.core.designsystem.theme.NexansTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexansTheme {
                NexansApp()
            }
        }
    }
}
