package com.lockwood.automata.intent

import android.Manifest.permission.CALL_PHONE
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.RequiresPermission
import com.lockwood.automata.android.buildIntent

fun Context.dialPhoneNumber(
    phoneNumber: String,
) {
    val uri = Uri.parse("tel:$phoneNumber")

    val intent = buildIntent(Intent.ACTION_DIAL, uri)

    startActivity(intent)
}

@RequiresPermission(CALL_PHONE)
fun Context.callPhoneNumber(
    phoneNumber: String,
) {
    val uri = Uri.parse("tel:$phoneNumber")

    val intent = buildIntent(Intent.ACTION_CALL, uri)

    startActivity(intent)
}