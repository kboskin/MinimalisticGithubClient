package com.github.minimalistic.presentation.core.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.visibility
import com.nullgr.core.ui.extensions.hideKeyboard
import com.nullgr.core.ui.extensions.setStatusBarColor
import dagger.android.support.AndroidSupportInjection
import me.dmdev.rxpm.base.PmSupportFragment
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.navigation.BackHandler
import com.github.minimalistic.presentation.core.navigation.FlowRouter
import com.github.minimalistic.presentation.core.navigation.RouterProvider
import com.github.minimalistic.presentation.core.pm.BasePm
import com.github.minimalistic.presentation.core.pm.factory.PmFactory
import com.github.minimalistic.presentation.core.pm.widgets.SnackBarControl
import com.github.minimalistic.presentation.core.pm.widgets.bind
import com.github.minimalistic.presentation.core.ui.dialog.createDialog
import com.github.minimalistic.presentation.core.ui.snack_bar_view.SnackBarData
import com.github.minimalistic.presentation.core.ui.state_view.StateView
import com.github.minimalistic.presentation.core.ui.system_ui.StatusBarConfigProvider
import com.github.minimalistic.presentation.utils.applyInsetsToContentView
import com.github.minimalistic.presentation.utils.makeSnackBar
import com.github.minimalistic.presentation.utils.makeSnackBarWithAction
import com.github.minimalistic.presentation.widgets.dialogs.ProgressDialog
import javax.inject.Inject

abstract class BaseFragment<T : BasePm> : PmSupportFragment<T>(), BackHandler {

    @Inject
    lateinit var factory: PmFactory

    protected abstract val screenLayout: Int

    protected abstract val classToken: Class<T>

    protected abstract val statusBarConfigProvider: StatusBarConfigProvider?

    protected open val backgroundColor: Int = R.color.color_window_background_grey

    open val progressDialog: ProgressDialog by lazy { ProgressDialog.newInstance() }
    open val router by lazy(LazyThreadSafetyMode.NONE) {
        ((parentFragment ?: activity) as RouterProvider).router as FlowRouter
    }

    private var emptyStateView: StateView? = null
    private var progressView: View? = null
    private var homeButtonView: View? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(screenLayout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        emptyStateView = view.findViewById<View>(R.id.emptyStateView) as? StateView
        progressView = view.findViewById(R.id.progressView)
        homeButtonView = view.findViewById(R.id.homeButtonView)
    }

    override fun onStart() {
        super.onStart()
        statusBarConfigProvider?.let {
            view?.applyInsetsToContentView(!it.drawUnderStatusBar)
            activity?.window?.setStatusBarColor(it.statusBarColor, it.lightStatusBar)
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.setBackgroundDrawableResource(backgroundColor)
    }

    override fun onPause() {
        view?.hideKeyboard()
        super.onPause()
    }

    override fun providePresentationModel(): T {
        val pm = factory.createViewModel(classToken)
        pm.router = router
        return pm
    }

    @CallSuper
    override fun onBindPresentationModel(pm: T) {
        emptyStateView?.let { stateView -> pm.emptyControl.bind(stateView, compositeUnbind) }
        progressView?.let { view -> pm.progressState.bindTo(view.visibility()) }
        homeButtonView?.clicks()?.bindTo { activity?.onBackPressed() }
        view?.let { view ->
            pm.snackBarControl.bindTo { data, control ->
                makeSnackBarWithAction(view, data, control)
            }
        }
    }

    override fun handleBack() {
        router.exit()
    }

    fun <T> SnackBarControl<T>.bindTo(createSnackBar: (data: T, sc: SnackBarControl<T>) -> Snackbar) {
        bind({ data, sc -> createSnackBar(data, sc) }, compositeUnbind)
    }

    private fun showSnackbar(data: SnackBarData) {
        view?.let { content ->
            makeSnackBar(content, data).show()
        }
    }
}