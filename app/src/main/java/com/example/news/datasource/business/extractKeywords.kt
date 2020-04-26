package com.example.news.datasource.business
import com.example.news.data.models.KeywordData
import com.example.news.extenstions.replaceSpecialSpace

/**
 *  뉴스 기사에서 키워드 추출하기
 */

/**
 * 키워드 추출 로직
 * 1. 띄어쓰기 기준으로 스크립트 분절
 * 2. 키워드 선정 적합성 검사(1글자 초과, 단어 존재) && 키워드 빈도수 체크
 * 3. 정렬하기 (나온횟수 많은 순 -> 사전순)
 */

fun extractKeywords(count: Int, description: String): ArrayList<KeywordData>  {
    val keywordMap = HashMap<String, Int>()
    val keywordList = ArrayList<KeywordData>()
    var index = 0



    description.let {
        it.replaceSpecialSpace()?.split("\\s+".toRegex())?.let {
            it.forEach { key ->
                if (key.length == 1 || key == "") { // 단어 1개 or 단어 없을 때 키워드 추출하지 않음
                    return@forEach
                }
                when (keywordMap.containsKey(key)) {
                    true -> {   // 중복된 단어인경우
                        keywordMap[key]?.run {
                            keywordList.get(this).count++
                        }
                    }
                    false -> {  // 중복되지 않은 단어인 경우
                        KeywordData(key, 1).let {
                            keywordList.add(it)
                            keywordMap[key] = index++
                        }
                    }
                }
            }
        }
    }

    when(keywordList.isEmpty()){
        true -> return ArrayList<KeywordData>()
        false -> return keywordList.sortedWith(keywordComparator).take(count) as ArrayList<KeywordData>
    }

}

/**
 *  키워드 우선순위 비교하기
 *  나온 횟수 -> 사전
 */
private val keywordComparator = Comparator<KeywordData> { a, b ->
    val count = a.count - b.count
    when {
        count > 0 -> -1
        count < 0 -> 1
        a.word > b.word -> 1
        else -> -1
    }
}