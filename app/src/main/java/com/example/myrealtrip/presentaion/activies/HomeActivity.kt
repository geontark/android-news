package com.example.myrealtrip.presentaion.activies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myrealtrip.R
import com.example.myrealtrip.databinding.ActivityHomeBinding
import com.example.myrealtrip.di.injectFeature
import com.example.myrealtrip.presentaion.adapters.NewsListAdapter
import com.example.myrealtrip.presentaion.viewmodels.HomeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  뉴스 기사 목록을 볼 수 있는 엑티비티
 */
class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private val mHomeVM: HomeVM by viewModel()
    private var mAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        injectFeature()

        mBinding.apply {
            lifecycleOwner = this@HomeActivity
            homeVM = mHomeVM
            newsList.adapter = mAdapter
        }
        mHomeVM.getNews()
    }
}