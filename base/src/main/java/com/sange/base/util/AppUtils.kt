package com.sange.base.util

import android.app.AlarmManager
import android.app.PendingIntent
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
    val isDebugMode: Boolean by lazy { isDebug() }

    /**
     * 获取是否debug版本
     */
    private fun isDebug() =
        Base.getContext().applicationInfo != null && Base.getContext().applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

    /**
     * 重启app
     * @param context
     */
    fun restartApp(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }else{
            LogUtils.e("AppUtils restartApp() error")
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
     * @param time 秒 触发时间 默认5秒
     * @param clazz 启动的Activity 示例：com.xxx.xxx.MainActivity
     */
    fun restartApp(time: Int = 5, clazz: String) {
        val cmd = "sleep ${time}; am start -N $clazz"
        val commandResult = ShellUtils.execCommand(cmd, true, true)
        LogUtils.e(
            StringBuilder().append("restartApp() successMsg:").append(commandResult.successMsg)
                .append(", ErrorMsg:").append(commandResult.errorMsg).toString()
        )
    }

    /**
     * 定时拉起应用(3秒)
     * @param time 秒 触发时间 默认3秒
     * @param clazz 启动的Activity 示例：MainActivity::class.java
     */
    fun autoRunApp(time: Int = 3, clazz: Class<Any>) {
        val context = Base.getContext()
        // 获取AlarmManager服务
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        // 设置触发时间 3秒
        val triggerAtMillis = System.currentTimeMillis() + time * 1000//1 * 60 * 1000
        // 创建Intent，用于指定要启动的Activity或Service
        val intent = Intent(context, clazz)
        // 使用PendingIntent包装Intent
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        // 设置单次闹钟
        alarmManager?.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }

    /**
     * 是否为APP进程
     * @param appPackageName APP包名
     */
    fun isAppProcess(appPackageName: String) =
        appPackageName == ProcessUtils.getProcessName(Base.getContext(), android.os.Process.myPid())
}