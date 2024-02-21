package com.sange.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * 底部导航栏（横条，返回键等样式的机型适配）
 * @param color 背景色，默认：黑色
 */
@Composable
fun NavigationBar(modifier: Modifier = Modifier, color: Color = Color.Black) {
    Box(modifier = modifier
        .fillMaxWidth()
        .background(color)) {
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}