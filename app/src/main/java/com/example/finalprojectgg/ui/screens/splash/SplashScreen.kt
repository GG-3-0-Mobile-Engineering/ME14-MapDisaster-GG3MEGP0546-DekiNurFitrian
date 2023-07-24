package com.example.finalprojectgg.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.R
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.timerTask

@Composable
fun SplashScreen(
    navToMainScreen: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(key1 = true) {
            delay(2000)
            navToMainScreen()
        }
        Image(
            painter = painterResource(id = R.drawable.img_brand_logo),
            contentDescription = "Logo Peta Bencana",
            modifier = Modifier.size(100.dp)
        )
    }
}