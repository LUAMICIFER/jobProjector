// File: SplashScreen.kt
package com.example.jobscrapper_v3.screens

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
import androidx.compose.ui.platform.LocalContext
import com.example.jobscrapper_v3.dataStore
import com.example.jobscrapper_v3.OnBoardingKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(3000)
        // Read the onboarding flag from DataStore
        val onboardingComplete = context.dataStore.data
            .map { preferences -> preferences[OnBoardingKey] ?: false }
            .first()
        if (onboardingComplete) {
            navController.navigate("HomeScreen") {
                popUpTo("SplashScreen") { inclusive = true }
            }
        } else {
            navController.navigate("OnBoardingScreen") {
                popUpTo("SplashScreen") { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val animationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}
