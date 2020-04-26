package com.example.news.datasource.remote

import com.example.news.data.contacts.NewsRemoteDataSource
import com.example.news.datasource.api.NewsApi
import com.example.news.data.mappers.mapperNewsDataEntity
import com.example.news.data.models.NewsData
import com.example.news.datasource.business.extractKeywords
import com.example.news.domain.entities.NewsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.lang.Exception

class NewsRemoteDataSourceImpl constructor(private val newsApi: NewsApi) : NewsRemoteDataSource {
    var defferList = ArrayList<Deferred<NewsData>>()

    /**
     *  구글 기사 요청
     */

    override suspend fun get(): ArrayList<NewsEntity> {
        val newsUrl = "https://news.google.com/rss"

//       뉴스 목록 요청 후 뉴스 제목과 링크 파싱
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

//       앞에서 추출한 link를 통해 썸네일, 설명글 얻어오기
        val defferList = ArrayList<Deferred<NewsData>>()
        for (newsData in list) {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                val article = newsData.link.let { newsApi.getNews(it) }

                newsData.thumbnail = article?.select("meta[property=og:image]")?.first()?.attr("content") ?: ""
                newsData.description = article?.select("meta[property=og:description]")?.first()?.attr("content") ?: ""
                newsData.keywords = extractKeywords(3,newsData.description)

                newsData
            }
            defferList.add(deferred)
        }

        this.defferList = defferList
        // 뉴스 정보 전부를 얻어오고 리턴
        val result = (defferList.map { mapperNewsDataEntity(it.await()) }) as ArrayList<NewsEntity>
        this.defferList = ArrayList<Deferred<NewsData>>()
        return result
    }

    override fun cancelGet(): Boolean {
        try {
            defferList.map { it.cancel() }
            defferList = ArrayList<Deferred<NewsData>>()
            return true
        }catch (e: Exception){
            return false
        }
    }
}