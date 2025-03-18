package com.example.jobscrapper_v3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class SideBarDataClass(
    val imgsrc: String,
    val title: String
)

@Composable
fun SideBarItems(modifier: Modifier = Modifier) {
    val items = listOf(
        SideBarDataClass(
            imgsrc = "https://logos-world.net/wp-content/uploads/2021/08/Android-Logo-2017-2019.png",
            title = "Android"
        ),
        SideBarDataClass(imgsrc = "https://img.freepik.com/free-psd/phone-icon-design_23-2151311666.jpg", title = "Web"),
        SideBarDataClass(
            imgsrc = "https://cdn-icons-png.flaticon.com/128/12602/12602187.png",
            title = "UI/UX"
        )
    )

    LazyColumn(modifier = modifier) {

        items(items) { item ->
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier.background(Color.DarkGray).clickable {

                }
            ){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top=4.dp, start=16.dp, end=16.dp,bottom=0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    AsyncImage(
                        model = item.imgsrc,
                        contentDescription = item.title,
                        modifier = Modifier.width(40.dp).height(40.dp)
                    )
                    Text(text = item.title)
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewSideBarItems() {
    SideBarItems(modifier = Modifier)
}
