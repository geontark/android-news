package com.example.news.presentaion.activies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.news.R
import com.example.news.databinding.ActivitySplashBinding
import com.example.news.presentaion.contacts.ISplashActivity
import com.example.news.presentaion.viewmodels.SplashVM

class SplashActivity : Activity() {
    private lateinit var mBinding: ActivitySplashBinding
    lateinit var mSplashVM: SplashVM

    private val mISplashActivity = object : ISplashActivity {
        override fun moveHomeActivity() {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        mBinding.apply {
            logoResourceId = R.drawable.news_logo
        }
        mSplashVM = SplashVM(mISplashActivity)
    }

    override fun onBackPressed() {

    }
}
