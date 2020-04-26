package com.example.news.data.contacts

import com.example.news.domain.entities.NewsEntity

interface NewsRemoteDataSource {
    suspend fun get(): ArrayList<NewsEntity>
    fun cancelGet(): Boolean
}