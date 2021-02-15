package com.lockwood.automata.intent

import android.Manifest.permission.SET_ALARM
import android.content.Context
import android.os.Build
import android.provider.AlarmClock
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.lockwood.automata.android.buildIntent

@RequiresPermission(SET_ALARM)
fun Context.createAlarm(
    message: String,
    hour: Int,
    minutes: Int,
) {
    val intent = buildIntent(AlarmClock.ACTION_SET_ALARM) {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
    }

    startActivity(intent)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@RequiresPermission(SET_ALARM)
fun Context.startTimer(
    message: String,
    seconds: Int,
    skipUi: Boolean = false,
) {
    val intent = buildIntent(AlarmClock.ACTION_SET_TIMER) {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_LENGTH, seconds)
        putExtra(AlarmClock.EXTRA_SKIP_UI, skipUi)
    }

    startActivity(intent)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Context.showAlarms() {
    val intent = buildIntent(AlarmClock.ACTION_SHOW_ALARMS)

    startActivity(intent)
}