package com.example.news.presentaion.mappers

import com.example.news.domain.entities.NewsEntity
import com.example.news.presentaion.models.NewsData

/**
 * NewEntity -> presentation 레이어의 NewData로 변환하기
 */
fun mapperNewsEntityData(newsEntity: NewsEntity): NewsData {
    return NewsData(
        newsEntity.title,
        newsEntity.description,
        newsEntity.link,
        newsEntity.thumbnail,
        newsEntity.keywords
    )
}
