package com.github.minimalistic.presentation.core.ui.state_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.visibility
import com.nullgr.core.ui.extensions.hide
import com.nullgr.core.ui.extensions.show
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import com.github.minimalistic.presentation.R

class StateWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), StateView {

    private var iconView: ImageView? = null
    private var titleView: TextView? = null
    private var descriptionView: TextView? = null
    private var buttonView: AppCompatButton? = null

    init {
        attrs?.let { set ->
            val array = context.obtainStyledAttributes(set, R.styleable.StateWidget, 0, 0)

            val layoutId = array.getResourceId(R.styleable.StateWidget_state_layout, 0)
            LayoutInflater.from(context).inflate(layoutId, this, true)

            iconView = findViewById(R.id.stateIconView)
            titleView = findViewById(R.id.stateTitleView)
            descriptionView = findViewById(R.id.stateDescriptionView)
            buttonView = findViewById(R.id.stateButtonView)

            val icon = array.getDrawable(R.styleable.StateWidget_state_icon)
            val title = array.getString(R.styleable.StateWidget_state_title)
            val description = array.getString(R.styleable.StateWidget_state_description)
            val button = array.getString(R.styleable.StateWidget_state_button)
            val bg = array.getDrawable(R.styleable.StateWidget_android_background)

            icon?.let { iconView?.setImageDrawable(it) }

            titleView?.text = title
            descriptionView?.text = description

            changeButtonVisibility(button)

            bg?.let { background = it }

            array.recycle()
        }
    }

    override fun state(): Consumer<in StateData> = Consumer { data ->
        with(data) {
            icon?.let { iconView?.setImageResource(it) }
            titleView?.text = title
            descriptionView?.text = description
            changeButtonVisibility(button)
        }
    }

    override fun clicks(): Observable<Unit> = buttonView?.clicks() ?: Observable.empty()

    override fun enable(): Consumer<in Boolean> = Consumer { enable ->
        buttonView?.isEnabled = enable
    }

    override fun visibility(): Consumer<in Boolean> = this.visibility(View.GONE)

    private fun changeButtonVisibility(button: String?) {
        if (!button.isNullOrBlank()) {
            buttonView?.show()
            buttonView?.text = button
        } else {
            buttonView?.hide()
        }
    }
}