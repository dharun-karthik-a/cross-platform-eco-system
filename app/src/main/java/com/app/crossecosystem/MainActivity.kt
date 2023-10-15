package com.app.crossecosystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.app.crossecosystem.enums.ToggleManager
import com.app.crossecosystem.enums.Toggles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val notificationToggleButton: SwitchCompat = findViewById(R.id.notification_toggle)
        val toggleManager = ToggleManager(applicationContext)

        notificationToggleButton.isChecked = toggleManager.isSwitchEnabled(Toggles.NOTIFICATION_TOGGLE)
        notificationToggleButton.setOnCheckedChangeListener { _, isChecked ->
            toggleManager.saveSwitchState(Toggles.NOTIFICATION_TOGGLE, isChecked)
        }
    }
}