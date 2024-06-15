package com.sange.base.util

import com.sange.base.util.permission.IPermissionsUtils
import com.sange.base.util.permission.XXPermissionsImpl

/**
 * 基础工具
 *
 * @author ssq
 */
object Base {

    /**
     * 动态权限申请 工具类
     */
    fun getPermissionsUtils(): IPermissionsUtils =  XXPermissionsImpl()
}