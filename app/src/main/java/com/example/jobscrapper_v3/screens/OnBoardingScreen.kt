package com.example.jobscrapper_v3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jobscrapper_v3.OnBoardingKey

import com.example.jobscrapper_v3.R
import com.example.jobscrapper_v3.dataStore

import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.round

@Composable
fun OnBoardingScreen(navController: NavController) {

    Text("OnBoardingScreen", color = Color.Black)
    val pages = listOf(
        PagesDataClass(
            title = "Welcome To JobScrapper!",
            description = "Your all-in-one solution to find the perfect job.",
            image = R.raw.page_1
        ),
        PagesDataClass(
            title = "Our Sources",
            description = "We work day and night to provide you with a variety of options for jobs and Internships",
            image = R.raw.page_2
        ),
        PagesDataClass(
            title = "The Reality Today..",
            description = "Unemployment is at its level, increasing due to various reasons like Global Recession in jobs. But!" +
                    "we are with you!!",
            image = R.raw.page_3
        ),
        PagesDataClass(
            title = "Welcome Aboard!",
            description = "Lets continue",
            image = R.raw.page_4
        )

    )


    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
    {
        val pagerState = rememberPagerState(pageCount = { pages.size })
       OnBoardingPager(items=pages, pagerState = pagerState, navController = navController)
    }
}


@Composable
fun OnBoardingPager(
    items: List<PagesDataClass>,
    pagerState: PagerState,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Unspecified)
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
    ){
        val textColorContrary = if (isSystemInDarkTheme()) Color.White else Color.Black
        HorizontalPager(state = pagerState) {page ->
            // Calculate offset manually:
            // If currentPage equals the page, then currentPageOffsetFraction is the offset
            // Otherwise, the difference between page indexes is added.
            val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            // Use kotlin.math.abs to avoid ambiguity:
            val absOffset = abs(pageOffset)

            // Compute scale: increase by up to 20% as the offset increases.
            val scale = 1f + 0.9f * absOffset
            // Compute alpha: fade out as offset increases.
            val alpha = 1f - absOffset

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally

            ){

                Box (
                    Modifier.fillMaxSize().weight(7f)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (isSystemInDarkTheme()) Color(0xD8151515) else Color.White)
                    ) {
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(
                                items[page].image
                            )
                        )
                        LottieAnimation(
                            modifier = Modifier.alpha(alpha)
                                .graphicsLayer(scaleX = scale, scaleY = scale),
                            composition = composition,
                            iterations = LottieConstants.IterateForever

                        )

                    }
                    if (pagerState.currentPage < items.size - 1) {
                        val coroutineScope = rememberCoroutineScope()
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    // Navigate to the last page (pages.size - 1)
                                    pagerState.animateScrollToPage(items.size - 1)
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .background(Color.Transparent)
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Text("Skip", color = Color.Red)
                        }
                    }

                    if (pagerState.currentPage > 0) {


                        val coroutineScope = rememberCoroutineScope()

                        IconButton(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }, modifier = Modifier.padding(10.dp)) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back",
                                tint = textColorContrary
                            )
                        }
                    }
                }
                PageIndicator(pagerState= pagerState,modifier = Modifier.weight(1f).background(if (isSystemInDarkTheme()) Color(0xD8151515) else Color.White),pageSize = items.size, selectedPage = pagerState.currentPage)




                Box (
                    Modifier
                        .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
                        .weight(3f)
                        .fillMaxSize()
                        .fillMaxHeight()
                ){
                    Column {
                        Text(
                            modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally),
                            text = items[page].title,
                            color = textColorContrary
                        )
                        Box(modifier = Modifier.fillMaxSize().weight(5f)) {

                            if (pagerState.currentPage != items.size - 1) {


                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = items[page].description,
                                    color = textColorContrary
                                )
                            }
                            else{
                                val context = LocalContext.current
                                val coroutineScope = rememberCoroutineScope()


                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            // Set onboarding complete flag to true in DataStore
                                            context.dataStore.edit { preferences ->
                                                preferences[OnBoardingKey] = true
                                            }
                                            // Navigate to HomeScreen and clear the back stack
                                            navController.navigate("HomeScreen") {
                                                popUpTo("OnBoardingScreen") { inclusive = true }
                                            }
                                        }
                                    },
                                    shape = RoundedCornerShape(5.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(86, 135, 182, 255), // your desired background color
                                        contentColor = Color.White // change to your desired text color
                                    ),
                                    modifier = Modifier.align(Alignment.Center)
                                ) {
                                    Text("Let's get Started..")
                                }

                            }
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier, pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color.Blue,
    unselectedColor: Color = Color.White,
    pagerState: PagerState

) {Box {


    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(bottom = 8.dp)
            .background(if (isSystemInDarkTheme()) Color(0xD8151515) else Color.White),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val pageOffset = ((pagerState.currentPage - iteration) + pagerState.currentPageOffsetFraction)
            // Use kotlin.math.abs to avoid ambiguity:
            val absOffset = abs(pageOffset)

            // Compute scale: increase by up to 20% as the offset increases.
            val scale = 20 + 0.9f * absOffset
            // Compute alpha: fade out as offset increases.
            val alpha = 40f + 10f*absOffset

            val color = if (pagerState.currentPage == iteration) Color.Blue else Color.LightGray
            val size = if (pagerState.currentPage == iteration) 16.dp else 10.dp
            val width = if (pagerState.currentPage == iteration) alpha.dp else 4.dp
            val verticalPadding = if (pagerState.currentPage == iteration) 0.dp else 8.dp
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .width(width)
                    .background(color)
                    .size(size)
            )
        }
    }
}
}