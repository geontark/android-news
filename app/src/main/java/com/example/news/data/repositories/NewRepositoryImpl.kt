package com.example.news.data.repositories

import com.example.news.data.contacts.NewsRemoteDataSource
import com.example.news.domain.contacts.NewsRepository
import com.example.news.domain.entities.NewsEntity

class NewRepositoryImpl constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun getNewsItems(): ArrayList<NewsEntity> {
        return newsRemoteDataSource.get()
    }

    override fun cancelGetNewsItems(): Boolean {
        return newsRemoteDataSource.cancelGet()
    }
}
