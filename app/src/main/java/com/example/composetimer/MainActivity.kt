package com.example.composetimer

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import com.example.composetimer.ui.components.CountDownScreen
import com.example.composetimer.ui.components.CountDownView
import com.example.composetimer.ui.main.MainViewModel
import com.example.composetimer.ui.theme.ComposeTimerTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTimerTheme {
                TransparentStatusBar(windows = window)
                CountDownScreen(viewModel)
            }
        }
    }
}

@Composable
fun TransparentStatusBar(windows: Window) =
    MaterialTheme {

        windows.statusBarColor = MaterialTheme.colors.surface.toArgb()
        windows.navigationBarColor = MaterialTheme.colors.surface.toArgb()

        @Suppress("DEPRECATION")
        if (MaterialTheme.colors.surface.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (MaterialTheme.colors.surface.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }