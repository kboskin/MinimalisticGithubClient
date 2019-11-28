@file:Suppress("NOTHING_TO_INLINE", "TooManyFunctions")

package com.github.minimalistic.presentation.core.pm

import com.nullgr.core.rx.bindProgress
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.skipWhileInProgress
import me.dmdev.rxpm.widget.dialogControl
import com.github.minimalistic.presentation.core.navigation.FlowRouter
import com.github.minimalistic.presentation.core.pm.listeners.ConnectionListener
import com.github.minimalistic.presentation.core.pm.widgets.ErrorHandler
import com.github.minimalistic.presentation.core.pm.widgets.stateControl
import com.github.minimalistic.presentation.core.pm.widgets.snackBarControl
import com.github.minimalistic.presentation.core.pm.widgets.errorHandler
import com.github.minimalistic.presentation.core.pm.widgets.networkControl
import com.github.minimalistic.presentation.core.ui.dialog.DialogData
import com.github.minimalistic.presentation.core.ui.dialog.DialogResult
import com.github.minimalistic.presentation.core.ui.snack_bar_view.SnackBarData
import com.github.minimalistic.presentation.core.ui.state_view.StateData
import java.util.concurrent.TimeUnit

abstract class BasePm(
    protected val services: ServiceFacade
) : PresentationModel() {

    lateinit var router: FlowRouter

    val progressState = State(false)
    val progressDialogState = State(false)

    val hideKeyBoardCommand = Command<Unit>()
    val showKeyBoardCommand = Command<Unit>()

    val retryAction = Action<Unit>()
    val networkStateAction = Action<Boolean>()
    val networkStateCommand = Command<Boolean>(bufferSize = 1)

    val errorControl = stateControl()
    val emptyControl = stateControl()
    val unauthorizedAction = Action<Unit>()
    val snackBarControl = snackBarControl<SnackBarData>()

    internal val resources = services.resources
    internal val network = services.network
    internal val bus = services.bus
    internal val errorParser = services.errorParser

    protected val errorHandler: ErrorHandler = errorHandler()

    private val networkControl by lazy { networkControl(network) }

    open val isEmptyScreen: Boolean = false

    override fun onCreate() {
        super.onCreate()

        if (this is ConnectionListener) {
            networkStateAction.observable
                .doOnNext { networkStateCommand.consumer.accept(it) }
                .subscribe()
                .untilDestroy()

            networkControl.observable
                .subscribe()
                .untilDestroy()
        }
    }

    internal fun showSnackBar(data: SnackBarData) {
        snackBarControl.show(data)
    }

    internal fun passToErrorContainer(data: StateData) {
        errorControl.dataState.consumer.accept(data)
    }

    internal fun passToEmptyStateContainer(data: StateData) {
        emptyControl.dataState.consumer.accept(data)
    }

    internal fun passToErrorViewVisibility(visible: Boolean) {
        errorControl.visibilityState.consumer.accept(visible)
    }

    internal fun passToUnauthorizedContainer() {
        unauthorizedAction.consumer.accept(Unit)
    }

    protected open fun handleError(error: Throwable) {
        errorHandler.handleError(error)
    }

    protected inline fun <T> Observable<T>.debounceAction(): Observable<T> =
        this.throttleFirst(ACTION_DEBOUNCE_MILLIS, TimeUnit.MILLISECONDS)

    protected inline fun <T> Observable<T>.longDebounceAction(): Observable<T> =
        this.throttleFirst(LONG_ACTION_DEBOUNCE_MILLIS, TimeUnit.MILLISECONDS)

    protected inline fun <T> Observable<T>.hideErrorContainer(): Observable<T> =
        this.doOnSubscribe { errorControl.visibilityState.consumer.accept(false) }

    protected inline fun <T> Single<T>.hideErrorContainer(): Single<T> =
        this.doOnSubscribe { errorControl.visibilityState.consumer.accept(false) }

    protected inline fun Completable.hideErrorContainer(): Completable =
        this.doOnSubscribe { errorControl.visibilityState.consumer.accept(false) }

    protected inline fun <T> Observable<T>.skipWhileInProgress(): Observable<T> =
        this.skipWhileInProgress(progressState.observable)

    protected inline fun <T> Observable<T>.bindProgress(): Observable<T> =
        this.bindProgress(progressState.consumer)

    protected inline fun <T> Single<T>.bindProgress(): Single<T> =
        this.bindProgress(progressState.consumer)

    protected inline fun Completable.bindProgress(): Completable =
        this.bindProgress(progressState.consumer)

    companion object {
        const val ACTION_DEBOUNCE_MILLIS = 1000L
        const val LONG_ACTION_DEBOUNCE_MILLIS = 1600L
        const val RELOAD_DELAY_MILLIS = 3000L
    }
}