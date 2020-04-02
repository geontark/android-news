package com.example.myrealtrip.presentaion.viewmodels

import com.example.myrealtrip.presentaion.contacts.ISplashActivity
import kotlinx.coroutines.*

class SplashVM(var ISplashActivity: ISplashActivity) {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1300L)
            ISplashActivity.moveHomeActivity()
        }
    }

}