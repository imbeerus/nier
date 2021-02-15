package com.lockwood.automata.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.lockwood.automata.android.buildIntent
import com.lockwood.automata.core.EMPTY

fun Context.showLocationOnMap(
    lat: String,
    lng: String,
    label: String = String.EMPTY,
) {
    val geoLocation = if (label.isEmpty()) {
        "geo:$lat,$lng"
    } else {
        "geo:0,0?q=$lat,$lng($label)"
    }

    val uri = Uri.parse(geoLocation)
    val intent = buildIntent(Intent.ACTION_VIEW, uri)

    startActivity(intent)
}