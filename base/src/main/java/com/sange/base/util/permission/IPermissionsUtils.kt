package com.sange.base.util.permission

import android.content.Context

/**
 * 动态申请权限 接口
 * （通过接口 隔离第三方权限申请库）
 * @author ssq
 */
interface IPermissionsUtils {

    companion object{
        /** 相机 使用描述 */
        var mCameraUseDesc = ""
        /** 录音 使用描述 */
        var mRecordUseDesc = ""
        /** 相册 使用描述 */
        var mImagesUseDesc = ""
        /** 定位 使用描述 */
        var mLocationUseDesc = ""
        /** 蓝牙 使用描述 */
        var mBluetoothUseDesc = ""
    }

    /**
     * 请求相机权限
     * @param onAllGranted 全部同意
     * @param onDenied 部分拒绝
     */
    fun requestCamera(context: Context, onAllGranted: () -> Unit, onDenied: () -> Unit)
    /**
     * 请求相机权限、录音权限
     * @param onAllGranted 全部同意
     * @param onDenied 部分拒绝
     */
    fun requestCameraRecord(context: Context, onAllGranted: () -> Unit, onDenied: () -> Unit)

    /**
     * 请求相机、相册、录音权限
     * @param onAllGranted 全部同意
     */
    fun requestCameraImagesRecord(context: Context, onAllGranted: () -> Unit)

    /**
     * 请求录音权限
     * @param onAllGranted 全部同意
     */
    fun requestRecord(context: Context, onAllGranted: () -> Unit)

    /**
     * 请求定位权限
     * @param onAllGranted 全部同意
     */
    fun requestLocation(context: Context, onAllGranted: () -> Unit)

    /**
     * 请求蓝牙权限
     * @param onAllGranted 全部同意
     */
    fun requestBluetooth(context: Context, onAllGranted: () -> Unit)

    /**
     * 检查相机权限 是否同意
     */
    fun isGrantedCamera(context: Context): Boolean
}