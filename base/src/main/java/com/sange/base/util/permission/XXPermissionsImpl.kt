package com.sange.base.util.permission

import android.content.Context
import com.hjq.permissions.XXPermissions
import com.hjq.permissions.permission.PermissionLists

/**
 * XXPermissions权限框架实现类
 * @author ssq
 */
class XXPermissionsImpl : IPermissionsUtils {
    override fun requestCamera(context: Context, onAllGranted: () -> Unit, onDenied: () -> Unit) {
        XXPermissions.with(context)
            .permission(PermissionLists.getCameraPermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                } else {
                    onDenied()
                }
            }
    }

    override fun requestCameraRecord(
        context: Context,
        onAllGranted: () -> Unit,
        onDenied: () -> Unit
    ) {
        XXPermissions.with(context)
            .permission(PermissionLists.getCameraPermission())
            .permission(PermissionLists.getRecordAudioPermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                } else {
                    onDenied()
                }
            }
    }

    override fun requestCameraImagesRecord(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            // 如果 targetSdk >= 33，则添加 Permission.READ_MEDIA_IMAGES 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
            // 如果 targetSdk < 33，则添加 Permission.READ_EXTERNAL_STORAGE 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
            .permission(PermissionLists.getCameraPermission())
            .permission(PermissionLists.getReadMediaImagesPermission())
            .permission(PermissionLists.getRecordAudioPermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                }
            }
    }

    override fun requestRecord(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(PermissionLists.getRecordAudioPermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                }
            }
    }

    override fun requestLocation(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(PermissionLists.getAccessCoarseLocationPermission())
            .permission(PermissionLists.getAccessFineLocationPermission())
            // 如果不需要在后台使用定位功能，请不要申请此权限
//                .permission(Permission.ACCESS_BACKGROUND_LOCATION)
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                }
            }
    }

    override fun requestBluetooth(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(PermissionLists.getBluetoothScanPermission())
            .permission(PermissionLists.getBluetoothConnectPermission())
            .permission(PermissionLists.getBluetoothAdvertisePermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                }
            }
    }

    override fun requestWriteStorage(context: Context, onAllGranted: () -> Unit) {
        XXPermissions.with(context)
            .permission(PermissionLists.getWriteExternalStoragePermission())
            .interceptor(PermissionInterceptor())
            .description(PermissionDescription())
            .request { _, deniedList ->
                if (deniedList.isEmpty()) {
                    onAllGranted()
                }
            }
    }

    override fun isGrantedCamera(context: Context): Boolean =
        XXPermissions.isGrantedPermissions(context, arrayOf(PermissionLists.getCameraPermission()))
}