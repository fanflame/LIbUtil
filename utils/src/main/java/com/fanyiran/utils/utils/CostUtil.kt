package com.fanyiran.utils.utils

import android.os.Handler
import android.os.SystemClock
import com.fanyiran.utils.LogUtil

class CostUtil(val TAG: String) {
    private var renderTime = 0f

    @Volatile
    private var renderAllTime = 0L

    @Volatile
    private var renderCount = 0
    private var handler: Handler? = null

    private var startTime = 0L

    init {
        handler = Handler()
        handler?.postDelayed(object : Runnable {
            override fun run() {
                LogUtil.v(TAG, renderTime.toString())
                handler?.postDelayed(this, 1000)
            }
        }, 1000)
    }

    fun start() {
        startTime = SystemClock.currentThreadTimeMillis()
    }

    fun end() {
        renderCount++
        renderAllTime += SystemClock.currentThreadTimeMillis() - startTime
        renderTime = renderAllTime * 1.0f / renderCount
        if (renderAllTime > 1000) {
            renderAllTime = 0
            renderCount = 0
        }
    }
}