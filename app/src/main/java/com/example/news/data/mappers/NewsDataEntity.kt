package com.example.news.data.mappers

import com.example.news.data.models.NewsData
import com.example.news.domain.entities.NewsEntity

//    data 레이어의 NewsData -> NewsEntity로 매핑
fun mapperNewsDataEntity(newsData: NewsData): NewsEntity {
    val keywords: ArrayList<String> = newsData.keywords.map { it.word } as ArrayList<String>
    return NewsEntity(
        newsData.title,
        newsData.description,
        newsData.link,
        newsData.thumbnail,
        keywords
    )
}