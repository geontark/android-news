package com.example.myrealtrip.data.contacts

import com.example.myrealtrip.domain.entities.NewsEntity

interface NewsRemoteDataSource {
    suspend fun get(): ArrayList<NewsEntity>
}