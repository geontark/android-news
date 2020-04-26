package com.example.news.presentaion.activies

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.news.R
import com.example.news.databinding.ActivityDetailBinding
import com.example.news.presentaion.models.NewsData
import com.example.news.presentaion.viewmodels.DetailVM

/**
 *  뉴스 정보를 자세히 볼 수 있는 엑티비티
 */
class DetailActivity : AppCompatActivity() {
    lateinit var mDetailVM: DetailVM
    private lateinit var mBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val item = intent.getParcelableExtra<Parcelable>("news") as NewsData
        mDetailVM = DetailVM(item)
        mBinding.detailVM = mDetailVM
    }
}
