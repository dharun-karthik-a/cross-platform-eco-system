package com.app.crossecosystem.enums

import android.content.Context

enum class Toggles {
    NOTIFICATION_TOGGLE
}

class ToggleManager(context: Context) {

    private val TOGGLE_PREFERENCES = "TOGGLE_PREFERENCES"
    private val sharedPref = context.getSharedPreferences(TOGGLE_PREFERENCES, Context.MODE_PRIVATE)

    fun saveSwitchState(toggleName: Toggles, toggleState: Boolean) {
        sharedPref.edit().putBoolean(toggleName.name, toggleState).apply()
    }

    fun isSwitchEnabled(toggleName: Toggles): Boolean {
        return sharedPref.getBoolean(toggleName.name, false)
    }

}