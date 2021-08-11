package com.lockwood.automata.core

object HashUtils {

    fun <T> calculateHashCode(data: T): Int {
        return data.safeHashCode()
    }

    fun <T> calculateHashCode(vararg data: T): Int {
        var result = data.first().safeHashCode()

        if (data.size > Int.SINGLE) {
            data.sliceArray(IntRange(Int.SINGLE, data.size - Int.SINGLE)).forEach {
                result = Int.BITES_OFFSET * result + it.hashCode()
            }
        }

        return result
    }

    private fun <T> T.safeHashCode(): Int = when (this) {
        is Array<*> -> contentHashCode()
        is List<*> -> contentHashCode()
        is Collection<*> -> toTypedArray().contentHashCode()
        else -> hashCode()
    }
}