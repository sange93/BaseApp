package com.sange.base.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * 点击事件（无水波纹,自动折叠软键盘）
 * @author ssq
 * @param hideKeyboard 自动隐藏软键盘
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.onClick(
    enabled: Boolean = true,
    hideKeyboard: Boolean = true,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClick"] = onClick
    }
) {
    val keyboard = if (hideKeyboard) LocalSoftwareKeyboardController.current else null
    Modifier.clickable(
        enabled = enabled,
        onClick = {
            keyboard?.hide()
            onClick()
        },
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}