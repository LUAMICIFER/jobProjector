package com.example.jobscrapper_v3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun TEMPSideBarItems(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var Categories by remember { mutableStateOf<List<sideBarItem>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                Categories = RetrofitInstance.api.getCategories()
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = "sidebar"){
        composable("jobPage") { JobPage("hello") }
//        composable(Routes.screenB) { ScreenB(navController) }
    }
    LazyColumn(modifier = modifier) {
        Categories?.let { list ->
            items(list) { item ->
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier.background(Color.DarkGray).clickable {
                        navController.navigate("JobPage")
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(item.link),
                            contentDescription = item.name,
                            modifier = Modifier.size(65.dp).weight(2f).padding(end = 20.dp)
                        )

                        Text(text = item.name,Modifier.weight(5f).padding(start = 20.dp))
                    }
                }

            }
        }?: item { Text(text = "Loading...", modifier = Modifier.padding(16.dp)) }
    }
}