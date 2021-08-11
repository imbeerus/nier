package com.lockwood.replicant.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.lockwood.replicant.delegate.fakeNotNull
import com.lockwood.replicant.delegate.resetFakeNotNullVar
import com.lockwood.replicant.feature.Releasable
import com.lockwood.replicant.feature.base.FeatureFragment
import com.lockwood.replicant.mvi.function.FunctionStore

abstract class ReplicantFragment<StateStore : Any> : FeatureFragment() {

    protected var store: StateStore by fakeNotNull()
        private set

    private val functionStore: FunctionStore = FunctionStore()

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store = initStateStore(savedInstanceState)
    }

    @CallSuper
    override fun onDestroyView() {
        releaseStateStore()
        functionStore.release()
        super.onDestroyView()
    }

    protected abstract fun initStateStore(savedInstanceState: Bundle?): StateStore

    protected fun <P1, R> remember(
        action: ((P1) -> R),
        first: P1,
    ) = functionStore.remember(first, action)

    protected fun <P1, P2, R> remember(
        action: ((P1, P2) -> R),
        first: P1,
        second: P2,
    ) = functionStore.remember(first, second, action)

    protected fun <P1, P2, P3, R> remember(
        action: ((P1, P2, P3) -> R),
        first: P1,
        second: P2,
        third: P3,
    ) = functionStore.remember(first, second, third, action)

    private fun releaseStateStore() {
        if (store is Releasable) {
            (store as Releasable).release()
        }

        resetFakeNotNullVar()
    }

}
