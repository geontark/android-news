package com.example.myrealtrip.domain.entities

data class NewsEntity(
    val title: String,
    val description: String,
    val link: String,
    val thumbnail: String,
    val keywords: ArrayList<String>
)