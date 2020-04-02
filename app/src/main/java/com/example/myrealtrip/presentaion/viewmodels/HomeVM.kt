package com.example.myrealtrip.presentaion.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myrealtrip.domain.usecases.NewsUseCase
import com.example.myrealtrip.presentaion.mappers.mapperNewsEntityData
import com.example.myrealtrip.presentaion.models.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM(private val newsUseCase: NewsUseCase) : ViewModel() {
    var newsItems = MutableLiveData<ArrayList<NewsData>>()
    var isLoad = MutableLiveData<Boolean>(false)

    /**
     *  뉴스 데이터 얻어오기 요청
     */
    fun getNews() {
        isLoad.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val list = newsUseCase.getNews().map { it -> mapperNewsEntityData(it) } as ArrayList<NewsData>
            CoroutineScope(Dispatchers.Main).launch {
                newsItems.value = list
                isLoad.value = false
            }
        }
    }
}