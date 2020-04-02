package com.example.myrealtrip.presentaion.mappers

import com.example.myrealtrip.domain.entities.NewsEntity
import com.example.myrealtrip.presentaion.models.NewsData

/**
 * NewEntity -> NewData로 변환하기
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
