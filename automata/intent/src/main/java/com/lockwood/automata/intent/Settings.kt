package com.lockwood.automata.intent

import android.content.Context
import android.provider.Settings
import com.lockwood.automata.android.buildIntent

fun Context.openSettings() {
    val intent = buildIntent(Settings.ACTION_SETTINGS)

    startActivity(intent)
}