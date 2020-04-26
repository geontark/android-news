package com.example.news.domain.usecases

import com.example.news.domain.contacts.NewsRepository
import com.example.news.domain.entities.NewsEntity

/**
 * news 관련 usecase 집합
 */
class NewsUseCase constructor(
    private val newsRepository: NewsRepository
) {
    //    news 리스트 가져오기
    suspend fun getNews(): ArrayList<NewsEntity> {
        return newsRepository.getNewsItems()
    }

    //    뉴스리스트 가져오기 취소하기
    fun cancelGetNews(): Boolean {
        return newsRepository.cancelGetNewsItems()
    }
}