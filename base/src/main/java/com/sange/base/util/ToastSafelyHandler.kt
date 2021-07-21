package com.sange.base.util

import android.os.Handler
import android.os.Message
import java.lang.Exception

/**
 * 捕获Toast在Android 7.x以下的奔溃异常：android.view.WindowManager$BadTokenException
 *
 * @author ssq
 */
class ToastSafelyHandler(private val handler: Handler): Handler(handler.looper) {
    override fun dispatchMessage(msg: Message) {
        try {
            super.dispatchMessage(msg)
        }catch (e: Exception){}
    }

    override fun handleMessage(msg: Message) {
        //需要委托给原Handler执行
        handler.handleMessage(msg)
    }
}