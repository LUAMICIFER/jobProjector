package com.example.jobscrapper_v3.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jobscrapper_v3.R
import kotlinx.coroutines.launch

@Composable
fun JobPage(cateogory:String){
    val scope = rememberCoroutineScope()
//    var JobPost by remember { mutableStateOf<List<post>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

//    LaunchedEffect(Unit) {
//        scope.launch {
//            try {
//                JobPost = RetrofitInstance.api.getInternshalaData(cateogory)
//            } catch (e: Exception) {
//                error = e.message
//            }
//        }
//    }
// Sample Data
    var JobPost by remember {
        mutableStateOf(
            listOf(
                post(
                    companyName = "NextEdge Labs",
                    imageLink = "https://internshala-uploads.internshala.com/logo%2F60c3763e414b21623422526.png.webp",
                    link = "https://internshala.com/job/detail/remote-associate-flutter-developer-job-at-nextedge-labs1742271475",
                    location = "Work from home",
                    title = "Associate Flutter Developer"
                )
            )
        )
    }

    LazyColumn {
        JobPost?.let { list ->
            items(list) { item ->
                    PostStructure(item)
            }
        }?: item { Text(text = "Loading...", modifier = Modifier.padding(16.dp)) }
    }

}

@Composable
fun PostStructure(item: post) {
    Row(Modifier){
        Column(Modifier.weight(7f)) {
            Text(text = item.title)
            Row(Modifier.padding(top=4.dp), horizontalArrangement = Arrangement.Start) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "location", modifier = Modifier.size(18.dp),tint = Color.Gray
                )
                Text(text = item.location)
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_location_city_24),
                    contentDescription = "location", modifier = Modifier.size(18.dp), tint = Color.Gray
                )
                Text(text = "${item.companyName} ")

            }
        }
        Image(
//                                painter = rememberAsyncImagePainter(item.imageLink),
            painter = if (item.link.startsWith("https")) {
                rememberAsyncImagePainter(item.imageLink) // Load from URL
            } else {
                painterResource(id = R.drawable.baseline_warehouse_24) // Load from drawable
            },
            contentDescription = item.imageLink,
            modifier = Modifier
                .size(50.dp)
                .weight(2f)
                .padding(end = 20.dp).weight(2f)
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun JobPagePreview () {
    JobPage("hello")
}

