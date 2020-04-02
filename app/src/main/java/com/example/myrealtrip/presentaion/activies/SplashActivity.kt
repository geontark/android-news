package com.example.myrealtrip.presentaion.activies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myrealtrip.R
import com.example.myrealtrip.databinding.ActivitySplashBinding
import com.example.myrealtrip.presentaion.contacts.ISplashActivity
import com.example.myrealtrip.presentaion.viewmodels.SplashVM

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

}
