package com.example.myrealtrip.datasource.models

class NewsData {
    var title = ""
    var description = ""
    var thumbnail = ""
    var link = ""
    var keywords = ArrayList<KeywordData>()

    //    description으로부터 count수 만큼 키워드 추출
    fun extractKeywords(count: Int) {
        val description = this.description
        if (this.description.isEmpty()) {
            return
        }
        val map = HashMap<String, Int>()    // key 값은 word value 는 keywordList에 저장된 word값의 인덱스값
        val keywordList = ArrayList<KeywordData>()
        val splitArray = stringReplace(description)?.split("\\s+".toRegex())

        var index = 0   // keywordList의 인덱
//        키워드 추출하기
        splitArray?.let {
            it.forEach {key ->

//                단어 1개 or 단어 없을 때 키워드 추출하지 않음
                if (key.length == 1 || key == "") {
                    return@forEach
                }

                when (map.containsKey(key)) {
                    true -> {   // 중복된 단어인경우
                        map[key]?.run {
                            keywordList.get(this).count++
                        }
                    }
                    false -> {  // 중복되지 않은 단어인 경우
                        KeywordData(key, 1).let {
                            keywordList.add(it)
                            map[key] = index++
                        }
                    }
                }
            }
        }

        val takeLength = if (keywordList.size < count) {
            keywordList.size
        } else {
            count
        }

        keywords = keywordList.sortedWith(keywordComparator).take(takeLength) as ArrayList<KeywordData>
    }

    /**
     *  특수문자를 " "로 변경하
     */
    fun stringReplace(str: String): String? {
        val match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"
        return str.replace(match.toRegex(), " ")
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
}
