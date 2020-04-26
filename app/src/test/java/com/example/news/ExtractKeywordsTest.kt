package com.example.news

import com.example.news.data.models.NewsData
import com.example.news.datasource.business.extractKeywords
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
        val maxCount = 3
        newsData.keywords = extractKeywords(maxCount,newsData.description)
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
        newsData.keywords = extractKeywords(maxCount,newsData.description)
        assertTrue(newsData.keywords.size == maxCount)
        assertTrue(newsData.keywords[0].word == "eleven" && newsData.keywords[0].count == 11)
        assertTrue(newsData.keywords[1].word == "열하나" && newsData.keywords[1].count == 11)
        assertTrue(newsData.keywords[2].word == "일곱" && newsData.keywords[2].count == 7)
    }

    /**
     * 한글 기사에서 키워드 추출하기
     */
    @Test
    fun articleKoKeyword(){
        newsData.description = "코로나19 확산에 따른 유럽 프로축구팀들의 임금 삭감이 이어지고 있다. 영국 정부는 “모두가 희생하는 이때 선수들도 삭감을 받아들여야 한다”고 압박하고 나섰다." +
                "스페인 프리메라리가의 아틀레티코 마드리드는 2일(현지시각) 선수단 임금의 70%를 삭감했다고 밝혔다." +
                "구단은 “1군 선수들과 지도자들이 자신의 소득에 타격을 받는 것을 수용했다. 급여 삭감 대상은 1군과 여자팀, B팀”이라고 밝혔다." +
                "구단은 “코로나19 확산으로 국가 비상사태가 선언되고 각종 활동이 연기되면서 경제적 생존 능력을 지켜야 한다. 클럽의 미래를 보장하고자 하는 일”이라고 덧붙였다." +
                "보통 구단의 고용 규제는 계약 중지나 근무시간 단축을 포함한다. 하지만 선수나 지도자에게는 적용되지 않는다. 이번엔 코로나19의 여파가 워낙 커 구단과 직원들의 생계가 걸리자, 선수단도 힘을 보탰다."

        val maxCount = 1000
        newsData.keywords = extractKeywords(maxCount,newsData.description)
        assertTrue(newsData.keywords[0].word == "구단은" && newsData.keywords[0].count == 2)
        assertTrue(newsData.keywords[1].word == "밝혔다" && newsData.keywords[1].count == 2)
        assertTrue(newsData.keywords[2].word == "이라고" && newsData.keywords[2].count == 2)
    }
}