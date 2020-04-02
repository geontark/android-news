package com.example.myrealtrip.api

import com.example.myrealtrip.datasource.api.NewsApi
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception

class NewsApiImpl : NewsApi {
    override fun getNews(url: String): Document? {
        try {
            return Jsoup.connect(url).get()
        }catch (e : Exception){
            e.printStackTrace()
        }
        return null
    }
}