package com.example.news.datasource.api

import org.jsoup.nodes.Document

interface NewsApi {
    fun getNews(url: String): Document?
}