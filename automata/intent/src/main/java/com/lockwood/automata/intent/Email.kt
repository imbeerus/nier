package com.lockwood.automata.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.lockwood.automata.android.buildIntent
import com.lockwood.automata.core.EMPTY
import com.lockwood.automata.core.SINGLE
import com.lockwood.automata.file.MIME_TYPES

fun Context.composeEmail(
    address: String,
    subject: String,
    body: String = String.EMPTY,
) {
    val mailUri = StringBuilder("mailto:$address?subject=$subject")

    if (body.isNotEmpty()) {
        val bodyWithLines = body.replace("\n", "<br>")
        mailUri.append("&body=$bodyWithLines")
    }

    val resultMailUri = Uri.parse(mailUri.toString())
    val intent = buildIntent(Intent.ACTION_SENDTO, resultMailUri)

    startActivity(intent)
}

fun Context.composeEmail(
    address: String,
    subject: String,
    vararg attachments: Uri,
) = composeEmail(
    addresses = arrayOf(address),
    subject = subject,
    attachments = attachments,
)

fun Context.composeEmail(
    addresses: Array<String>,
    subject: String,
    vararg attachments: Uri,
) {
    val sendAction = if (attachments.size == Int.SINGLE) {
        Intent.ACTION_SEND
    } else {
        Intent.ACTION_SEND_MULTIPLE
    }

    val intent = buildIntent(sendAction) {
        type = MIME_TYPES.ANY_MIME_TYPE
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_STREAM, attachments)
    }

    startActivity(intent)
}
