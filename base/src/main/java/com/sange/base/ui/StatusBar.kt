package com.sange.base.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import com.sange.base.util.SysBarUtils

/**
 * 状态栏
 * @param isLight 是否为亮色模式（黑色图标）
 */
@Composable
fun StatusBar(isLight: Boolean = false) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            SysBarUtils.setSysBarForget(view, isLight)
        }
    }
}