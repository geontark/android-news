package com.example.news.domain.contacts

import com.example.news.domain.entities.NewsEntity

interface NewsRepository {
    suspend fun getNewsItems(): ArrayList<NewsEntity>
    fun cancelGetNewsItems(): Boolean
}