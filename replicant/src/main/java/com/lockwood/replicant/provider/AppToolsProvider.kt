package com.lockwood.replicant.provider

import androidx.lifecycle.ViewModelProvider
import com.lockwood.replicant.executor.ExecutorProvider
import com.lockwood.replicant.res.ResourceReader

interface AppToolsProvider : ApplicationProvider {

    fun getViewModelFactory(): ViewModelProvider.Factory

    fun getResourceReader(): ResourceReader

    fun getExecutorProvider(): ExecutorProvider
}