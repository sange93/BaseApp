package com.sange.base.ui

import android.os.Bundle
import android.os.StrictMode
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.sange.base.BaseApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Activity 基类（公共通用型）
 * MV ViewModel 架构
 *
 * @author ssq
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope() {

    /** 是否分发触摸事件。true 屏幕可点击；false 屏幕不可点击*/
    protected var mIsDispatchTouchEvent = true

    /** 严格模式测试 true 开启 false 关闭 */
    protected var mStrictModeEnable = false

    /**
     * ViewBinding 实例
     */
    protected lateinit var mBinding: VB

    /**
     * 提供ViewBinding对象
     */
    protected abstract fun providerVB(): VB

    /**
     * 初始化界面
     */
    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = providerVB()
        setContentView(mBinding.root)
        initView()
        // 严格模式测试（生产环境不要开启）
        enableStrictMode(mStrictModeEnable && BaseApplication.isDebugMode)
    }

    /**
     * 开启严格模式调试
     *
     * @param isEnable 是否开启
     */
    private fun enableStrictMode(isEnable: Boolean) {
        if (!isEnable) return
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }

    /**
     * Touch手势分发
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return isDispatchTouchEvent(ev)
    }

    /**
     * 是否分发触摸事件
     */
    protected fun isDispatchTouchEvent(ev: MotionEvent?) = if (mIsDispatchTouchEvent) {
        super.dispatchTouchEvent(ev)
    } else {
        false
    }

    override fun onDestroy() {
        cancel()// 取消协程
        super.onDestroy()
    }
}