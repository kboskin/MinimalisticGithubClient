package com.github.minimalistic.presentation.core.pm.widgets

import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import me.dmdev.rxpm.PresentationModel

class SnackBarControl<T> internal constructor(pm: PresentationModel) {

    val displayed = BehaviorRelay.create<Display>()
    private val result = PublishRelay.create<Unit>()

    init {
        displayed.accept(Display.Absent)
    }

    fun show(data: T) {
        dismiss()
        displayed.accept(Display.Displayed(data))
    }

    fun showForResult(data: T): Maybe<Unit> {
        dismiss()

        return result
            .doOnSubscribe {
                displayed.accept(Display.Displayed(data))
            }
            .takeUntil(
                displayed
                    .skip(1)
                    .filter { it == Display.Absent }
            )
            .firstElement()
    }

    fun sendResult() {
        this.result.accept(Unit)
        dismiss()
    }

    fun dismiss() {
        if (displayed.value != null && displayed.value is Display.Displayed<*>) {
            displayed.accept(Display.Absent)
        }
    }

    sealed class Display {
        data class Displayed<T>(val data: T) : Display()
        object Absent : Display()
    }
}

fun <T> PresentationModel.snackBarControl(): SnackBarControl<T> = SnackBarControl(this)

internal inline fun <T> SnackBarControl<T>.bind(
    crossinline createSnackBar: (data: T, dc: SnackBarControl<T>) -> Snackbar,
    compositeDisposable: CompositeDisposable
) {

    var snackbar: Snackbar? = null
    val callback: BaseTransientBottomBar.BaseCallback<Snackbar> =
        object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                dismiss()
            }
        }

    val closeDialog: () -> Unit = {
        snackbar?.removeCallback(callback)
        snackbar?.dismiss()
        snackbar = null
    }

    compositeDisposable.add(
        displayed
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { closeDialog() }
            .subscribe {
                @Suppress("UNCHECKED_CAST")
                if (it is SnackBarControl.Display.Displayed<*>) {
                    snackbar = createSnackBar(it.data as T, this)
                    snackbar?.addCallback(callback)
                    snackbar?.show()
                } else if (it === SnackBarControl.Display.Absent) {
                    closeDialog()
                }
            }
    )
}
