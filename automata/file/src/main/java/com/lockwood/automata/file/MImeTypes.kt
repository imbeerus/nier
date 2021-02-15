package com.lockwood.automata.file

import android.webkit.MimeTypeMap

object MImeTypes {

    const val ANY_MIME_TYPE = "*/*"

    fun getFileExtensionFromUrl(url: String): String {
        return MimeTypeMap.getFileExtensionFromUrl(url)
    }

    @kotlin.jvm.Throws(IllegalArgumentException::class)
    fun getExtensionFromMimeType(mimeType: String): String {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
            ?: error("There is no extension from mimeType: $mimeType")
    }

    fun getMimeTypeFromExtension(extension: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: ANY_MIME_TYPE
    }

}
