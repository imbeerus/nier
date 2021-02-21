package com.lockwood.replicant.ext

import android.app.Activity
import androidx.fragment.app.Fragment
import com.lockwood.replicant.ReplicantApplication
import com.lockwood.replicant.provider.AppToolsProvider

val Activity.appToolsProvider: AppToolsProvider
    get() = (applicationContext as ReplicantApplication).getAppToolsProvider()

val Fragment.appToolsProvider: AppToolsProvider
    get() = requireActivity().appToolsProvider