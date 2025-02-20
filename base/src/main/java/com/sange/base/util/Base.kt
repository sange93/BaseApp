package com.sange.base.util

import android.content.Context
import com.sange.base.util.permission.IPermissionsUtils
import com.sange.base.util.permission.XXPermissionsImpl
import java.lang.ref.WeakReference

/**
 * 基础工具
 *
 * @author ssq
 */
object Base {
    private lateinit var mContext: WeakReference<Context>

    /**
     * 初始化框架
     */
    fun init(context: Context){
        mContext = WeakReference(context)
    }

    fun getContext(): Context{
        if(this::mContext.isInitialized){
            val context = mContext.get()
            if (context != null){
                return context
            }else{
                throw Exception("请先调用 Base.init(context) 初始化Context")
            }
        }else{
            throw Exception("请先调用 Base.init(context) 初始化Context")
        }
    }

    /**
     * 动态权限申请 工具类
     */
    fun getPermissionsUtils(): IPermissionsUtils =  XXPermissionsImpl()
}