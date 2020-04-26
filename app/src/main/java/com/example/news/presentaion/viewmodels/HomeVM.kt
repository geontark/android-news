package com.example.news.presentaion.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.domain.usecases.NewsUseCase
import com.example.news.presentaion.mappers.mapperNewsEntityData
import com.example.news.presentaion.models.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeVM(private val newsUseCase: NewsUseCase) : ViewModel() {
    val newsItems = MutableLiveData<ArrayList<NewsData>>()
    val isLoad = MutableLiveData<Boolean>(false)
    private var job: Job = Job()

    /**
     *  뉴스 데이터 요청
     */
    fun getNews() {
        isLoad.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val list = newsUseCase.getNews().map { mapperNewsEntityData(it) } as ArrayList<NewsData>
            CoroutineScope(Dispatchers.Main).launch {
                newsItems.value = list
                isLoad.value = false
            }
        }
    }

    fun cancelGetNews() {
        isLoad.value?.let {
            if (it) {
                newsUseCase.cancelGetNews()
                job.cancel()
                isLoad.value = false
            }
        }
    }

}