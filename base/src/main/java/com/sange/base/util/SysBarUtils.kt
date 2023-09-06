package com.sange.base.util

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

/**
 * 系统‘顶部状态栏’，‘底部导航栏’工具类
 *
 * @author ssq
 */
object SysBarUtils {

    /**
     * 设置系统顶部底部栏 前景色
     */
    fun setSysBarForget(view: View, isLight: Boolean = false){
        WindowCompat.getInsetsController((view.context as Activity).window, view).run {
            // 状态栏是否为亮色模式；true亮色模式(黑色图标)；false暗色模式(白色图标)
            isAppearanceLightStatusBars = isLight
            // 底部导航栏是否为亮色模式
            isAppearanceLightNavigationBars = isLight
        }
    }

    /**
     * 设置系统顶部底部栏 背景色--透明
     */
    fun setSysBarBackgroundTransparent(context: Context){
        (context as Activity).window.run {
            // 设置状态栏背景色
            statusBarColor = Color.Transparent.toArgb()
            // 设置底部导航栏颜色
            navigationBarColor = Color.Transparent.toArgb()//Color.Black.toArgb()
        }
    }
}