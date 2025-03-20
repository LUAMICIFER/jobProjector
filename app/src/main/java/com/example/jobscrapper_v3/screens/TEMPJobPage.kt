package com.example.jobscrapper_v3.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jobscrapper_v3.R
import com.example.jobscrapper_v3.card.CardBuilder
import kotlinx.coroutines.launch



import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun JobPage(categori: String) {
    val category = Uri.decode(categori)
    val context = LocalContext.current
    var jobPost by remember { mutableStateOf<List<post>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Initial load
    LaunchedEffect(category) {
        isRefreshing = true
        try {
            val response = RetrofitInstance.api.getInternshalaData(category)
            Log.d("API_RESPONSE", response.toString())
            jobPost = response
        } catch (e: Exception) {
            Log.e("API_ERROR", e.message ?: "Unknown error")
            error = e.message
        } finally {
            isRefreshing = false
        }
    }

    // SwipeRefresh wrapping the LazyColumn
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                try {
                    val response = RetrofitInstance.api.getInternshalaData(category)
                    jobPost = response
                } catch (e: Exception) {
                    error = e.message
                } finally {
                    isRefreshing = false
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            jobPost?.let { list ->
                items(list) { item ->
                    CardBuilder(item, context)
                }
            } ?: item {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun PostStructure(item: post, context: android.content.Context) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(7f)
                .clickable {
                    // Open link when clicked

                }
        ) {
            Text(text = item.title)
            Row(modifier = Modifier.padding(top = 4.dp), horizontalArrangement = Arrangement.Start) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "location",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )
                Text(text = item.location)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_location_city_24),
                    contentDescription = "location",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )
                Text(text = item.companyName)
            }
        }
        Image(
            painter = if (item.link.startsWith("https")) {
                rememberAsyncImagePainter(item.imageLink)
            } else {
                painterResource(id = R.drawable.baseline_warehouse_24)
            },
            contentDescription = item.imageLink,
            modifier = Modifier
                .size(50.dp)
                .weight(2f)
                .padding(end = 20.dp)
        )
    }
}





@Preview(showBackground = true)
@Composable
private fun JobPagePreview () {
    JobPage("hello")
}

