package com.lockwood.automata.intent

import android.content.Context
import android.content.Intent
import com.lockwood.automata.android.buildIntent
import com.lockwood.automata.android.wrapWithChooser
import com.lockwood.automata.file.MimeTypes

fun Context.shareText(
    text: String,
) = buildIntent(Intent.ACTION_SEND) {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, text)

    startActivity(wrapWithChooser())
}