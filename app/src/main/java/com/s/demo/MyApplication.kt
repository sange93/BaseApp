package com.s.demo

import com.sange.base.BaseApplication


/**
 * 基础Application
 * @author ssq
 */
class MyApplication : BaseApplication() {

    override fun getAppPackageName(): String = packageName

    override fun initApp() {
        super.initApp()
    }
}