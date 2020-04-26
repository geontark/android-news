package com.example.news.domain.entities

// news에 대한 정보를 담는 entity
data class NewsEntity(
    val title: String,
    val description: String,
    val link: String,
    val thumbnail: String,
    val keywords: ArrayList<String>
)