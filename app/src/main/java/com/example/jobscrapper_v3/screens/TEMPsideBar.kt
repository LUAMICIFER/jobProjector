package com.example.jobscrapper_v3.screens

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun TEMPSideBarItems(navController:NavController) {
    val scope = rememberCoroutineScope()
    var Categories by remember { mutableStateOf<List<sideBarItem>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
//        scope.launch {
            try {
                Categories = RetrofitInstance.api.getCategories()
//                val response = RetrofitInstance.api.getCategories()
//                Log.d("API_RESPONSE", response.toString()) // Debugging
//                Categories = response
            } catch (e: Exception) {
//                Log.e("API_ERROR", e.message ?: "Unknown error") // Log errors
                error = e.message
            }
//        }
    }
//    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = "sidebar") {

//    }
    LazyColumn() {
        Categories?.let { list ->
            items(list) { item ->
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .clickable {
                            navController.navigate("jobPage/${Uri.encode(item.path)}")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(item.link),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(65.dp)
                                .weight(2f)
                                .padding(end = 20.dp)
                        )

                        Text(text = item.name,
                            Modifier
                                .weight(5f)
                                .padding(start = 20.dp))
                    }
                }

            }
        }?: items(8){ ShimmerSidebarItem() }
    }
}
@Composable
fun ShimmerSidebarItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.6f)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()
        )
    }
}

@Composable
fun Modifier.shimmerEffect(): Modifier {
    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.5f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.5f)
    )
    val transition = rememberInfiniteTransition()
    val translateX by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return this
        .graphicsLayer {
            alpha = 0.8f
        }
        .background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateX, 0f),
                end = Offset(translateX + 300f, 0f)
            )
        )
}