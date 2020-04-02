package com.example.myrealtrip.presentaion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * presentation 레이어에서 사용되는 NewaData 클래스
 */
@Parcelize
data class NewsData(
    val title: String,
    val description: String,
    val link: String,
    val thumbnail: String,
    val keywords: ArrayList<String>
) : Parcelable