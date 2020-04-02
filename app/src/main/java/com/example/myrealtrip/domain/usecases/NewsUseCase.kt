package com.example.myrealtrip.domain.usecases

import com.example.myrealtrip.domain.contacts.NewsRepository
import com.example.myrealtrip.domain.entities.NewsEntity

class NewsUseCase constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun getNews(): ArrayList<NewsEntity> {
        return newsRepository.getNewsItems()
    }
}