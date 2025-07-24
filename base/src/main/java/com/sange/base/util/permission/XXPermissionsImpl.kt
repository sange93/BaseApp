package com.sange.base.util.permission

import android.content.Context
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

/**
 * XXPermissions权限框架实现类
 * @author ssq
 */
class XXPermissionsImpl: IPermissionsUtils {
    override fun requestCamera(context: Context, onAllGranted: () -> Unit, onDenied: () -> Unit) {
        XXPermissions.with(context)
            .permission(Permission.CAMERA)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mCameraUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
                    onDenied()
                }
            })
    }

    override fun requestCameraRecord(
        context: Context,
        onAllGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        XXPermissions.with(context)
            .permission(Permission.CAMERA)
            .permission(Permission.RECORD_AUDIO)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mCameraUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
                    onDenied()
                }
            })
    }

    override fun requestCameraImagesRecord(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            // 如果 targetSdk >= 33，则添加 Permission.READ_MEDIA_IMAGES 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
            // 如果 targetSdk < 33，则添加 Permission.READ_EXTERNAL_STORAGE 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
            .permission(Permission.CAMERA)
            .permission(Permission.READ_MEDIA_IMAGES)
            .permission(Permission.RECORD_AUDIO)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mCameraUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
                }
            })
    }

    override fun requestRecord(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(Permission.RECORD_AUDIO)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mCameraUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
                }
            })
    }

    override fun requestLocation(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(Permission.ACCESS_COARSE_LOCATION)
            .permission(Permission.ACCESS_FINE_LOCATION)
            // 如果不需要在后台使用定位功能，请不要申请此权限
//                .permission(Permission.ACCESS_BACKGROUND_LOCATION)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mLocationUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
//                    ToastUtils.showShort(context.getString(R.string.permission_get_location_fail));
                }
            })
    }

    override fun requestBluetooth(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(Permission.BLUETOOTH_SCAN)
            .permission(Permission.BLUETOOTH_CONNECT)
            .permission(Permission.BLUETOOTH_ADVERTISE)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mBluetoothUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
//                    ToastUtils.showShort(context.getString(R.string.permission_get_bluetooth_fail));
                }
            })
    }

    override fun requestStorage(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(Permission.Group.STORAGE)
            .interceptor(PermissionInterceptor(IPermissionsUtils.mStorageUseDesc))
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if(all){
                        onAllGranted()
                    }
                }

                override fun onDenied(
                    permissions: MutableList<String>,
                    doNotAskAgain: Boolean
                ) {
                }
            })
    }

    override fun isGrantedCamera(context: Context): Boolean = XXPermissions.isGranted(context, Permission.CAMERA)
}