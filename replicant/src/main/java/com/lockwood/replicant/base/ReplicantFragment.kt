package com.lockwood.replicant.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.lockwood.replicant.event.*
import com.lockwood.replicant.view.MessageView
import com.lockwood.replicant.view.ext.requireScreenView

abstract class ReplicantFragment() : Fragment(), MessageView {

    private companion object {

        private val TAG = ReplicantFragment::class.simpleName
    }

    @CallSuper
    protected open fun onOnEvent(event: Event) {
        Log.d(TAG, "onOnEvent: $event")
        when (event) {
            is MessageEvent -> showMessage(event.message)
            is ErrorMessageEvent -> showError(event.message)
            is GoToBackEvent -> requireScreenView().goBack()
            is ShowScreenEvent -> requireScreenView().showScreen(event.screen)
        }
    }

}