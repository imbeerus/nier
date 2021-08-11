package com.lockwood.replicant.base

import androidx.annotation.CallSuper
import com.lockwood.automata.android.asApplication
import com.lockwood.automata.core.notSafeLazy
import com.lockwood.permission.IPermissionManager
import com.lockwood.permission.PermissionManager
import com.lockwood.permission.callback.RequestPermissionsResultCallback
import com.lockwood.replicant.feature.base.FeatureActivity
import com.lockwood.replicant.screen.Screen
import com.lockwood.replicant.view.ScreenView

abstract class ScreenActivity : FeatureActivity(), ScreenView {

    private companion object {

        private val TAG = FeatureActivity::class.simpleName
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun showScreen(screen: Screen) {
        error("Unknown screen: $screen")
    }

}

abstract class ReplicantActivity<State> : ScreenActivity(), RequestPermissionsResultCallback {

    private val permissionsManager: IPermissionManager by notSafeLazy {
        PermissionManager(applicationContext.asApplication())
    }

    @CallSuper
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        val isGranted = permissionsManager.isPermissionsGranted(this, requestCode, grantResults)
        val result = permissions.zip(grantResults.toList()).toMap()

        onRequestPermissionsResult(grantResults = result)
        onRequestPermissionsResult(isAllPermissionsGranted = isGranted)
    }

    protected abstract fun renderState(viewState: State)

    protected fun requestPermissions(vararg permissions: String) {
        permissionsManager.requestPermissions(this, *permissions)
    }

    protected fun hasPermissions(vararg permissions: String): Boolean {
        return permissionsManager.hasPermissions(*permissions)
    }

}
