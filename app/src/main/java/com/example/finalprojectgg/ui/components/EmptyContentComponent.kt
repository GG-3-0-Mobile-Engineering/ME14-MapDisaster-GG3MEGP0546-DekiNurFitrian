package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Preview(showBackground = true)
@Composable
fun EmptyContentView() {
    Box(modifier = Modifier.size(80.dp).testTag("Empty")){
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/02c8dcb7-9820-4fe2-b27f-e97294c9e442/AK1EsMHm44.json"))
        LottieAnimation(modifier = Modifier.fillMaxSize(),composition = composition, iterations = LottieConstants.IterateForever)
    }
    
}