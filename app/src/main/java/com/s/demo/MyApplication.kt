package com.s.demo

import com.sange.base.BaseApplication


/**
 * 基础Application
 * @author ssq
 */
class MyApplication : BaseApplication() {
    companion object {
        // app实例
        val instance by lazy { BaseApplication.instance }
    }

    override fun getAppPackageName(): String = packageName

    override fun initApp() {
        super.initApp()
    }
}