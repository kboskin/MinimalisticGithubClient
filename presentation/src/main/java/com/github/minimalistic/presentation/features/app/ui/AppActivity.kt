package com.github.minimalistic.presentation.features.app.ui

import android.os.Bundle
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.ui.activity.BaseActivity
import com.github.minimalistic.presentation.features.app.pm.AppPm

class AppActivity : BaseActivity<AppPm>() {

    override val screenLayout: Int = R.layout.layout_container
    override val classToken: Class<AppPm> = AppPm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        presentationModel.coldStartAction.consumer.accept(Unit)
    }
}