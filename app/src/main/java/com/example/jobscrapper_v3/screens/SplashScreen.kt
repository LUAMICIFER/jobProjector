package com.example.jobscrapper_v3.screens

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jobscrapper_v3.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect (Unit){
        delay(3000)

        navController.navigate("OnBoardingScreen"){popUpTo("SplashScreen") {inclusive = true}}
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
    ){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val animationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever

        )

    }
}