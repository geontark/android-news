package com.example.myrealtrip.data.repositories

import android.util.Log
import com.example.myrealtrip.data.contacts.NewsRemoteDataSource
import com.example.myrealtrip.domain.contacts.NewsRepository
import com.example.myrealtrip.domain.entities.NewsEntity

class NewRepositoryImpl constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun getNewsItems(): ArrayList<NewsEntity> {
        return newsRemoteDataSource.get()
    }
}
