package com.app.crossecosystem.firebase

import android.util.Log
import com.app.crossecosystem.notification.NotificationData
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FirebaseService(){

    private val TAG = "FIREBASE"

    fun publishNotification(notificationData: NotificationData) {
        val ts = getTimeStamp()
        Log.i(TAG,"publishing notification: $notificationData")
        val dbRef = database.getReference("notification").child(ts)
        dbRef.setValue(notificationData)
        Log.i(TAG,"published: $notificationData")
    }

    private fun getTimeStamp(formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")) =
        LocalDateTime.now().format(formatter)

    var database = FirebaseDatabase.getInstance()

}