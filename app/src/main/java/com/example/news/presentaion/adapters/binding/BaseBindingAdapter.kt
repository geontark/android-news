package com.example.news.presentaion.adapters.binding

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BaseBindingAdapter {
    /**
     * 당겨서 새로고침
     */
    @JvmStatic
    @BindingAdapter("refreshing")
    fun isLoading(view: SwipeRefreshLayout, isLoading: Boolean) {
        view.isRefreshing = isLoading
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
     * 웹뷰 관련 설정 및 웹뷰 url 설정
     */
    @JvmStatic
    @BindingAdapter("load_webview")
    fun webUrl(webView: WebView, url: String) {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webView.loadUrl(url)
    }
}