package com.example.jobscrapper_v3.card

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jobscrapper_v3.R
import com.example.jobscrapper_v3.screens.post

@Composable
fun CardBuilder(item: post, context: android.content.Context) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Yellow)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                context.startActivity(intent)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(7f)
                    .clickable {
                        // Open link when clicked
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                        context.startActivity(intent)
                    }
            ) {
                Text(text = item.title)
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
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
}
