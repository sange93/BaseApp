package com.s.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.sange.base.ui.BaseCompActivity
import com.sange.base.util.Base
import com.sange.base.util.LogUtils
import com.sange.base.util.ToastUtils
import com.sange.base.util.permission.IPermissionsUtils

/**
 * 单Activity + Compose架构
 * @author ssq
 */
class MainActivity : BaseCompActivity() {
    /** 权限申请工具 */
    private val mPermissionsUtils: IPermissionsUtils by lazy { Base.getPermissionsUtils() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle the splash screen transition.
        installSplashScreen()
        // 将内容显示在标题栏下
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mStrictModeEnable = true//TODO 开发模式：严格

        setContent {
            Surface(modifier = Modifier
                .fillMaxSize(),
                color = Color.White,
                contentColor = Color.White
            ) {
                Box(modifier = Modifier.statusBarsPadding().fillMaxSize()){
                    Button(onClick = { getLocationPremiss() }) {
                        Text(text = "获取定位权限")
                    }
                }
            }
        }
    }

    /**
     * 获取定位权限
     */
    private fun getLocationPremiss() {
        mPermissionsUtils.requestLocation(this){
            LogUtils.i("getLocationPremiss", "获取定位权限 成功")
            ToastUtils.showShort("获取定位权限成功")
        }
    }
}