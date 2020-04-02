package com.example.myrealtrip.datasource.mappers

import com.example.myrealtrip.datasource.models.NewsData
import com.example.myrealtrip.domain.entities.NewsEntity

//    NewsData -> NewsEntity로 변경
fun mapperNewsDataEntity(newsData: NewsData): NewsEntity {
    var keywords: ArrayList<String> = newsData.keywords.map { it.word } as ArrayList<String>
    return NewsEntity(
        newsData.title,
        newsData.description,
        newsData.link,
        newsData.thumbnail,
        keywords
    )
}