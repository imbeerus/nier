package com.lockwood.replicant

import com.lockwood.replicant.provider.AppToolsProvider
import com.lockwood.replicant.provider.ApplicationProvider

interface ReplicantApplication : ApplicationProvider {

    fun getAppToolsProvider(): AppToolsProvider
}