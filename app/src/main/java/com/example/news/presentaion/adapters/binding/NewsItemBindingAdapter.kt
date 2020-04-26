package com.example.news.presentaion.adapters.binding

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.presentaion.adapters.NewsListAdapter
import com.example.news.presentaion.models.NewsData

object NewsItemBindingAdapter {

    /**
     *  뉴스 기사 목록 정보를 리스트 어댑터에 넘겨준다
     */
    @JvmStatic
    @BindingAdapter("bind_news_items")
    fun setBindItems(view: RecyclerView, items: MutableLiveData<ArrayList<NewsData>>) {
        items.value?.let {
            (((view.adapter) ?: NewsListAdapter()) as NewsListAdapter).apply {
                this.items = it
                notifyDataSetChanged()
            }
        }
    }

    /**
     * 뉴스 키워드 뷰에 표시하기
     */
    @JvmStatic
    @BindingAdapter("bind_keywords")
    fun setKeywords(layout: ViewGroup, keywords: ArrayList<String>) {
        layout.children.forEach {
            it.visibility = View.INVISIBLE
        }

        keywords.forEachIndexed { i, keyword ->
            layout.getChildAt(i).visibility = View.VISIBLE
            if ((layout.getChildAt(i)) is Button) {
                (layout.getChildAt(i) as Button).text = keyword
            }
        }
    }

    /**
     *  이미지
     */
    @JvmStatic
    @BindingAdapter("bind_image")
    fun setImage(view: ImageView, imageUrl: String) {
        Glide
            .with(view)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.news_item)
            .error(R.drawable.news_item)
            .into(view);
    }
}