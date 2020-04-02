package com.example.myrealtrip.datasource.remote

import android.util.Log
import com.example.myrealtrip.data.contacts.NewsRemoteDataSource
import com.example.myrealtrip.datasource.api.NewsApi
import com.example.myrealtrip.datasource.mappers.mapperNewsDataEntity
import com.example.myrealtrip.datasource.models.NewsData
import com.example.myrealtrip.domain.entities.NewsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NewsRemoteDataSourceImpl constructor(private val newsApi: NewsApi) : NewsRemoteDataSource {

    /**
     *  구글 기사 요청
     */
    override suspend fun get(): ArrayList<NewsEntity> {
        val newsUrl = "https://news.google.com/rss"

        val doc = newsApi.getNews(newsUrl)
        val items = doc?.select("item")
        val list = ArrayList<NewsData>()

        items?.map {
            NewsData().apply {
                title = it.select("title").text()
                link = it.select("link").text()
                list.add(this)
            }
        }

//        썸네일, 설명글 얻어오기
        val defferList = ArrayList<Deferred<NewsData>>()
        for (newsData in list) {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                val article = newsApi.getNews(newsData.link)
                newsData.thumbnail =
                    article?.select("meta[property=og:image]")?.first()?.attr("content") ?: ""
                newsData.description =
                    article?.select("meta[property=og:description]")?.first()?.attr("content") ?: ""
                newsData.extractKeywords(3)
                newsData
            }
            defferList.add(deferred)
        }

        // 뉴스 정보 전부를 얻어오고 리턴
        return (defferList.map { mapperNewsDataEntity(it.await()) }) as ArrayList<NewsEntity>
    }
}