package com.lockwood.replicant.provider

import android.content.Context

interface ApplicationProvider {

    fun getApplicationContext(): Context
}