package com.example.jobscrapper_v3.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobscrapper_v3.screens.pages.SideBarItems


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        var isOpen = remember { mutableStateOf(false) }

        if (isOpen.value) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .background(Color.Transparent)
                    .clickable {

                        // Dismiss the sidebar when the overlay is clicked
                        isOpen.value = false
                    }
            )
        }

        AnimatedVisibility(
            visible = isOpen.value,
            enter = slideInHorizontally { fullWidth -> -fullWidth } + fadeIn(),
            exit = slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut(),
            modifier = Modifier.align(Alignment.CenterStart)

        ) {
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(60.dp))
                    SideBarItems(modifier = Modifier)
                }

            }
        }


        // Menu button to toggle the sidebar
        IconButton(
            onClick = {
                isOpen.value = !isOpen.value
                      },
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(60.dp)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                Modifier.size(70.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
