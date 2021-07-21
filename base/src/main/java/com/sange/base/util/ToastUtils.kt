package com.sange.base.util

import android.os.Build
import android.os.Handler
import android.widget.Toast
import com.sange.base.BaseApplication
import java.lang.reflect.Field

/**
 * Toast提示工具类
 *
 * @author ssq
 */
object ToastUtils {
    private val mSdk: Int by lazy { Build.VERSION.SDK_INT }
    private lateinit var mFieldTN: Field
    private lateinit var mFieldTNHandler: Field

    init {
        //安卓7.0的做处理，其它版本系统的不用处理
        if (mSdk <= Build.VERSION_CODES.N_MR1 && mSdk > Build.VERSION_CODES.M) {
            try {
                mFieldTN = Toast::class.java.getDeclaredField("mTN")
                mFieldTN.isAccessible = true
                mFieldTNHandler = mFieldTN.type.getDeclaredField("mHandler")
                mFieldTNHandler.isAccessible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showShort(msg: String) {
        show(msg, Toast.LENGTH_SHORT)
    }

    fun showLong(msg: String) {
        show(msg, Toast.LENGTH_LONG)
    }

    private fun show(msg: String, duration: Int) {
        val toast = Toast.makeText(BaseApplication.instance, msg, duration)
        setHook(toast)
        toast.show()
    }

    /**
     * 解决Toast在Android 7.x以下的奔溃异常：android.view.WindowManager$BadTokenException
     */
    private fun setHook(toast: Toast) {
        //安卓7.0的做处理，其它版本系统的不用处理
        if (mSdk <= Build.VERSION_CODES.N_MR1 && mSdk > Build.VERSION_CODES.M) {
            try {
                val tn = mFieldTN.get(toast)
                val preHandler: Handler = mFieldTNHandler.get(tn) as Handler
                mFieldTNHandler.set(tn, ToastSafelyHandler(preHandler));
            } catch (e: Exception) {
            }
        }
    }
}