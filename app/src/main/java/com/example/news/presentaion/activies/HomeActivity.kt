package com.example.news.presentaion.activies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.news.R
import com.example.news.databinding.ActivityHomeBinding
import com.example.news.di.injectFeature
import com.example.news.extenstions.networkCheck
import com.example.news.presentaion.adapters.NewsListAdapter
import com.example.news.presentaion.viewmodels.HomeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  뉴스 기사 목록을 볼 수 있는 엑티비티
 */
class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var mBinding: ActivityHomeBinding
    private val mHomeVM: HomeVM by viewModel()
    private var mAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        System.out.println("----HomeActivity---")


        injectFeature()

        mBinding.apply {
            lifecycleOwner = this@HomeActivity
            homeActivity = this@HomeActivity
            homeVM = mHomeVM
            newsList.adapter = mAdapter
        }

        when (networkCheck()) { // 네트워크 확인후 뉴스 데이터 요청
            true -> {
                mHomeVM.getNews()
            }
            false -> Toast.makeText(
                this,
                resources.getText(R.string.toast_network_check),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHomeVM.cancelGetNews()
    }

    //    당겨서 새로고침
    fun refreshListener() {
        when (networkCheck()) { // 네트워크 확인후 뉴스 데이터 요청
            true -> {
                mHomeVM.getNews()
            }
            false -> {
                Toast.makeText(
                    this,
                    resources.getText(R.string.toast_network_check),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //        뒤로가기 버튼
    override fun onBackPressed() {
        mHomeVM.isLoad.value?.let {
            when (it) {
                true -> mHomeVM.cancelGetNews()
                false -> finish()
            }
        }
    }
}