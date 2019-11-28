package com.github.minimalistic.presentation.features.search.ui

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.nullgr.core.ui.extensions.toggleView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.ui.fragment.BaseFragment
import com.github.minimalistic.presentation.core.ui.system_ui.StatusBarConfigProvider
import com.github.minimalistic.presentation.core.ui.system_ui.TransparentStatusBarConfigProvider
import com.github.minimalistic.presentation.features.search.pm.SearchPm
import com.github.minimalistic.presentation.utils.visibility

class SearchFragment : BaseFragment<SearchPm>() {
    override val screenLayout: Int = R.layout.fragment_search
    override val classToken: Class<SearchPm> = SearchPm::class.java
    override val statusBarConfigProvider: StatusBarConfigProvider? = TransparentStatusBarConfigProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarActionButtonView.setImageResource(R.drawable.img_github)
        toolbarActionButtonView.toggleView(true)
    }

    override fun onBindPresentationModel(pm: SearchPm) {
        super.onBindPresentationModel(pm)

        pm.searchNameInput.bindTo(searchInputLayout)

        performSearchView.clicks().bindTo(pm.searchNameAction)
        pm.buttonVisibilityState.bindTo { performSearchView.isEnabled = it }
        pm.progressState.bindTo(progressDialog.visibility(childFragmentManager))
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}