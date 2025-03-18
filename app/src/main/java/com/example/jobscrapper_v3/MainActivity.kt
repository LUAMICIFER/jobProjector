package com.example.jobscrapper_v3

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobscrapper_v3.screens.HomeScreen
import com.example.jobscrapper_v3.screens.JobPage
import com.example.jobscrapper_v3.screens.OnBoardingPager
import com.example.jobscrapper_v3.screens.OnBoardingScreen
import com.example.jobscrapper_v3.screens.SplashScreen
import com.example.jobscrapper_v3.screens.TEMPSideBarItems
import com.example.jobscrapper_v3.ui.theme.JobScrapper_V3Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val OnBoardingKey = booleanPreferencesKey("OnBoardingKey")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()


        val onBoarding: Flow<Boolean> = baseContext.dataStore.data
            .map { preferences ->
                preferences[OnBoardingKey] ?: false
            }

        getActionBar()?.hide()
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        //WindowCompat.setDecorFitsSystemWindows(window, false)// for adjusting according to navigation layot and status bar



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
                    composable("sidebar") { TEMPSideBarItems(navController) }

                    composable("jobPage/{path}") { backStackEntry ->
                        val path = backStackEntry.arguments?.getString("path") ?: ""
                        JobPage(path)
                    }
                }

               // Index(navController, onBoarding: Flow<Boolean>)
            }
        }
    }
}


