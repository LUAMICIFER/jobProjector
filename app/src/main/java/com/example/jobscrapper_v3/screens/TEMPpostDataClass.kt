package com.example.jobscrapper_v3.screens
//
//data class post(val companyName:String,val imageLink:String,val link:String,val location:String,val title:String)
import com.google.gson.annotations.SerializedName

data class post(
    @SerializedName("company_name") val companyName: String,
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("link") val link: String,
    @SerializedName("location") val location: String,
    @SerializedName("title") val title: String
)