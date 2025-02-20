package com.sange.base.util

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo

/**
 * APP高级工具类
 *
 * @author ssq
 */
object AppUtils {
    // 是否为debug模式
    val isDebugMode: Boolean by lazy { isDebugMode() }

    /**
     * 获取是否debug版本
     */
    private fun isDebugMode() =
        Base.getContext().applicationInfo != null && Base.getContext().applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

    /**
     * 重启app
     * @param context
     */
    fun restartApp(context: Context) {
        val packageManager = context.packageManager
        if (null == packageManager) {
            LogUtils.e("null == packageManager")
            return
        }
        val intent = packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    /**
     * 重启Android
     */
    fun reboot() {
        val commandResult: ShellUtils.CommandResult = ShellUtils.execCommand("reboot", true, true)
        LogUtils.e(
            StringBuilder().append("reboot() successMsg:").append(commandResult.successMsg)
                .append(", ErrorMsg:").append(commandResult.errorMsg).toString()
        )
    }

    /**
     * 延时重启APP
     */
    fun restartApp() {
        val cmd = "sleep 5; am start -N com.zjzn.vending.MainActivity"
        val commandResult = ShellUtils.execCommand(cmd, true, true)
        LogUtils.e(
            StringBuilder().append("restartApp() successMsg:").append(commandResult.successMsg)
                .append(", ErrorMsg:").append(commandResult.errorMsg).toString()
        )
    }

    /**
     * 是否为APP进程
     * @param appPackageName APP包名
     */
    fun isAppProcess(appPackageName: String) = appPackageName == ProcessUtils.getProcessName(Base.getContext(), android.os.Process.myPid())
}