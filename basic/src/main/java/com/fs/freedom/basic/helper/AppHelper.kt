package com.fs.freedom.basic.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.fs.freedom.basic.constant.CommonConstant
import com.fs.freedom.basic.util.LogUtil
import java.lang.RuntimeException

/**
 * App 帮助类
 */
object AppHelper {

    /**
     * 进入当前应用-设置-详情页面
     */
    fun intoAppSettingDetail(activity: Activity): Boolean {
        return try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.fromParts("package", activity.packageName, null)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
            true
        } catch (runtimeException: RuntimeException) {
            if (LogUtil.isCanLog) {
                runtimeException.printStackTrace()
            }
            false
        }
    }

    /**
     * 获取应用版本号
     */
    fun getAppVersion(context: Context?) : String {
        if (context == null) {
            return CommonConstant.UNKNOWN
        }
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            if (LogUtil.isCanLog) {
                e.printStackTrace()
            }
            CommonConstant.UNKNOWN
        }
    }

}