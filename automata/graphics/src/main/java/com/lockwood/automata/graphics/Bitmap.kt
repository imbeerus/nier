package com.lockwood.automata.graphics

import android.graphics.Bitmap
import com.lockwood.automata.core.SINGLE
import com.lockwood.automata.core.ZERO
import java.util.*

fun Bitmap.cropTransparent(): Bitmap {
    var empty = IntArray(width)
    var buffer = IntArray(width)
    Arrays.fill(empty, Int.ZERO)

    var top = Int.ZERO
    var left =  Int.ZERO
    var bottom = height
    var right = width

    for (y in Int.ZERO until height) {
        getPixels(buffer, Int.ZERO, width, Int.ZERO, y, width, Int.SINGLE)
        if (!empty.contentEquals(buffer)) {
            top = y
            break
        }
    }

    for (y in height - 1 downTo top + 1) {
        getPixels(buffer, Int.ZERO, width, Int.ZERO, y, width, Int.SINGLE)
        if (!empty.contentEquals(buffer)) {
            bottom = y
            break
        }
    }

    val bufferSize = bottom - top + 1
    empty = IntArray(bufferSize)
    buffer = IntArray(bufferSize)
    Arrays.fill(empty, Int.ZERO)

    for (x in Int.ZERO until width) {
        getPixels(buffer, Int.ZERO, Int.SINGLE, x, top + 1, Int.SINGLE, bufferSize)
        if (!empty.contentEquals(buffer)) {
            left = x
            break
        }
    }

    Arrays.fill(empty, Int.ZERO)
    for (x in width - 1 downTo left + 1) {
        getPixels(buffer, Int.ZERO, Int.SINGLE, x, top + 1, Int.SINGLE, bufferSize)
        if (!empty.contentEquals(buffer)) {
            right = x
            break
        }
    }

    return Bitmap.createBitmap(
        this,
        left,
        top,
        right - left,
        bottom - top
    )
}