package com.example.myrealtrip.presentaion.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myrealtrip.R
import com.example.myrealtrip.presentaion.models.NewsData

object BindingAdapter {
    /**
     * 웹뷰 관련 설정 및 웹뷰 url 설정
     */
    @JvmStatic
    @BindingAdapter("loadWebView")
    fun webUrl(webView: WebView, url: String) {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webView.loadUrl(url)
    }

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

    /**
     * 이미지 원형 크롭
     */
    @JvmStatic
    @BindingAdapter("circle_crop")
    fun circleImageCrop(view: ImageView, resourceId: Int) {
        Glide.with(view)
            .load(resourceId)
            .circleCrop()
            .apply(
                RequestOptions
                    .centerCropTransform()
                    .circleCrop()
            )
            .into(view)
    }

    /**
     * 당겨서 새로고침
     */
    @JvmStatic
    @BindingAdapter("refreshing")
    fun isLoading(view: SwipeRefreshLayout, isLoading: Boolean) {
        view.isRefreshing = isLoading
    }
}