package com.example.jobscrapper_v3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobscrapper_v3.screens.HomeScreen
import com.example.jobscrapper_v3.screens.OnBoardingPager
import com.example.jobscrapper_v3.screens.OnBoardingScreen
import com.example.jobscrapper_v3.screens.SplashScreen
import com.example.jobscrapper_v3.ui.theme.JobScrapper_V3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            JobScrapper_V3Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "SplashScreen") {
                    composable("SplashScreen") {
                        SplashScreen(navController)
                    }
                    composable("OnBoardingScreen") {
                        OnBoardingScreen(navController)
                    }
                    composable("HomeScreen") {
                        HomeScreen(navController)
                    }
                }

                Index(navController)
            }
        }
    }
}


