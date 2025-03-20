package com.example.jobscrapper_v3.screens

import com.google.gson.annotations.SerializedName

data class post(
    @SerializedName("company_name")val companyName:String,
    @SerializedName("image_link")val imageLink:String,
    @SerializedName("link")val link:String,
    @SerializedName("location")val location:String,
    @SerializedName("title")val title:String
)
