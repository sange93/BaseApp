package com.sange.base.util

import java.lang.StringBuilder
import java.util.*

/**
 * 字节数组扩展类
 * @author ssq
 */

/**
 * 字节数组转换16进制字符串
 */
fun ByteArray.toHexString(): String{
    val sb = StringBuilder()
    this.forEach {
        var hex = Integer.toHexString(it and 0xFF)
        if (hex.length == 1) {
            hex = "0$hex"
        }
        sb.append(hex.uppercase(Locale.getDefault()))
    }
    return sb.toString()
}