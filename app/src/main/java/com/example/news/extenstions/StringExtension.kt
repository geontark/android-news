package com.example.news.extenstions

// String 확장함수

/**r
 *  특수문자를 " "로 변경하기
 */
fun String.replaceSpecialSpace(): String? {
    val match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]"
    return replace(match.toRegex(), " ")
}
