package com.github.minimalistic.presentation.core.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.visibility
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.dmdev.rxpm.base.PmSupportActivity
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.navigation.AppNavigator
import com.github.minimalistic.presentation.core.navigation.BackHandler
import com.github.minimalistic.presentation.core.navigation.FlowRouter
import com.github.minimalistic.presentation.core.navigation.RouterProvider
import com.github.minimalistic.presentation.core.pm.BasePm
import com.github.minimalistic.presentation.core.pm.factory.PmFactory
import com.github.minimalistic.presentation.core.pm.widgets.SnackBarControl
import com.github.minimalistic.presentation.core.pm.widgets.bind
import com.github.minimalistic.presentation.core.ui.fragment.BaseFragment
import com.github.minimalistic.presentation.core.ui.snack_bar_view.SnackBarData
import com.github.minimalistic.presentation.core.ui.state_view.StateView
import com.github.minimalistic.presentation.utils.makeSnackBar
import com.github.minimalistic.presentation.utils.makeSnackBarWithAction
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

@Suppress("TooManyFunctions")
@SuppressLint("MissingSuperCall")
abstract class BaseActivity<T : BasePm> : PmSupportActivity<T>(),
    HasSupportFragmentInjector,
    BackHandler,
    RouterProvider {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: PmFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: FlowRouter

    protected abstract val screenLayout: Int

    protected abstract val classToken: Class<T>

    protected open val navigator: Navigator = AppNavigator(this)

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.containerView) as? BaseFragment<*>

    private var emptyStateView: StateView? = null
    private var progressView: View? = null
    private var homeButtonView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(screenLayout)

        emptyStateView = findViewById<View>(R.id.emptyStateView) as? StateView
        progressView = findViewById(R.id.progressView)
        homeButtonView = findViewById(R.id.homeButtonView)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        if (currentFragment != null) {
            currentFragment?.handleBack()
        } else {
            handleBack()
        }
    }

    override fun onBindPresentationModel(pm: T) {
        emptyStateView?.let { stateView -> pm.emptyControl.bind(stateView, compositeUnbind) }
        progressView?.let { view -> pm.progressState.bindTo(view.visibility()) }
        homeButtonView?.clicks()?.bindTo { onBackPressed() }
        pm.snackBarControl.bindTo { data, control ->
            makeSnackBarWithAction(window.decorView.findViewById(R.id.content), data, control)
        }
    }

    override fun providePresentationModel(): T {
        val pm = factory.createViewModel(classToken)
        pm.router = router
        return pm
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun handleBack() {
        router.exit()
    }

    fun <T> SnackBarControl<T>.bindTo(createSnackBar: (data: T, sc: SnackBarControl<T>) -> Snackbar) {
        bind({ data, sc -> createSnackBar(data, sc) }, compositeUnbind)
    }

    private fun showSnackbar(data: SnackBarData) {
        findViewById<View>(android.R.id.content)?.let { content ->
            makeSnackBar(content, data).show()
        }
    }
}