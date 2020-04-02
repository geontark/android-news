package com.example.myrealtrip.domain.contacts

import com.example.myrealtrip.domain.entities.NewsEntity

interface NewsRepository {
    suspend fun getNewsItems(): ArrayList<NewsEntity>
}