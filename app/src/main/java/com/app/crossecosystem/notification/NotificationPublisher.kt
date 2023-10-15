package com.app.crossecosystem.notification

import android.app.Notification
import android.content.pm.PackageManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.app.crossecosystem.enums.ToggleManager
import com.app.crossecosystem.enums.Toggles
import com.app.crossecosystem.firebase.FirebaseService


class NotificationPublisher() : NotificationListenerService() {

    private val TAG = "NOTIFICATION"
    private val firebaseService = FirebaseService()
    private lateinit var toggleManager: ToggleManager
    override fun onCreate() {
        super.onCreate()
        toggleManager = ToggleManager(applicationContext)
    }
    override fun onNotificationPosted(statusBarNotification: StatusBarNotification) {
        Log.i(TAG, "notification received for ${statusBarNotification.packageName}")
        if (!statusBarNotification.isGroup && toggleManager.isSwitchEnabled(Toggles.NOTIFICATION_TOGGLE)) {
            firebaseService.publishNotification(createNotificationData(statusBarNotification))
        }
    }

    private fun createNotificationData(statusBarNotification: StatusBarNotification): NotificationData {
        val notificationExtras = statusBarNotification.notification.extras
        return NotificationData(
            getAppName(statusBarNotification.packageName),
            notificationExtras.getCharSequence(Notification.EXTRA_TITLE, "unknown").toString(),
            notificationExtras.getCharSequence(Notification.EXTRA_TEXT, "").toString()

        )
    }

    private fun getAppName(packageName: String) = try {
        val pm = packageManager
        val applicationInfo =
            pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        pm.getApplicationLabel(applicationInfo).toString()

    } catch (e: java.lang.Exception) {
        packageName
    }

}

data class NotificationData(
    val packageName: String,
    val title: String,
    val content: String
)