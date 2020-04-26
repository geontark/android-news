package com.example.news.presentaion.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.presentaion.models.NewsData

class DetailVM(newsData: NewsData) : ViewModel() {
    val title = MutableLiveData<String>().apply { value = newsData.title }
    val webViewUrl = MutableLiveData<String>().apply { value = newsData.link }
    val keywords = MutableLiveData<ArrayList<String>>().apply { value = newsData.keywords }
}