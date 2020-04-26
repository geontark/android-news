package com.example.news.data.models

/**
 * data 레이어에 쓰이는 데이터 클래스
 */
class NewsData() {
    var title: String = ""
    var description: String = ""
    var thumbnail: String = ""
    var link: String = ""
    var keywords: ArrayList<KeywordData> = ArrayList<KeywordData>()
}