package com.github.minimalistic.presentation.features.flow.ui

import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.ui.fragment.BaseFlowFragment
import com.github.minimalistic.presentation.features.flow.pm.MainFlowPm

class MainFlowFragment : BaseFlowFragment<MainFlowPm>() {

    override val screenLayout: Int = R.layout.layout_container
    override val classToken: Class<MainFlowPm> = MainFlowPm::class.java

    companion object {
        fun newInstance() = MainFlowFragment()
    }
}