package com.example.myrealtrip

import com.example.myrealtrip.datasource.models.NewsData
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * 키워드 추출 관련 기능 테스트
 */
class ExtractKeywordsTest {
    lateinit var newsData: NewsData

    @Before
    fun init() {
        newsData = NewsData()
    }

    /**
     * 한글자 단어 키워드에서 제외하기
     */
    @Test
    fun oneCharactersExcept() {
        newsData.description = "일 일 일 일 일 일 일 일 일 일 일 일 일 일 일 일 일 일 " +
                "이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 이 " +
                "a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a " +
                "뉴스 뉴스 뉴스 뉴스 " +
                "신문지 신문지 "
        newsData.extractKeywords(3)
        assertTrue(newsData.keywords.size == 2)
        assertTrue(newsData.keywords[0].word == "뉴스" && newsData.keywords[0].count == 4)
        assertTrue(newsData.keywords[1].word == "신문지" && newsData.keywords[1].count == 2)
    }

    /**
     *   단어 수 같으면 영어 -> 한글, 사전순으로 키워드 추출
     */
    @Test
    fun orderKeywords() {
        newsData.description =
            "하나 " +
                    "다섯 다섯 다섯 다섯 다섯 " +
                    "여섯 여섯 여섯 여섯 여섯 여섯 " +
                    "일곱 일곱 일곱 일곱 일곱 일곱 일곱 " +
                    "열하나 열하나 열하나 열하나 열하나 열하나 열하나 열하나 열하나 열하나 열하나 " +
                    "eleven eleven eleven eleven eleven eleven eleven eleven eleven eleven eleven"
        val maxCount = 3
        newsData.extractKeywords(maxCount)
        assertTrue(newsData.keywords.size == maxCount)
        assertTrue(newsData.keywords[0].word == "eleven" && newsData.keywords[0].count == 11)
        assertTrue(newsData.keywords[1].word == "열하나" && newsData.keywords[1].count == 11)
        assertTrue(newsData.keywords[2].word == "일곱" && newsData.keywords[2].count == 7)
    }
}