package com.github.minimalistic.presentation

import androidx.fragment.app.Fragment
import com.github.minimalistic.presentation.features.dispay_user_info.ui.DisplayUserInfoFragment
import com.github.minimalistic.presentation.features.flow.ui.MainFlowFragment
import com.github.minimalistic.presentation.features.search.model.UserWithReposModel
import com.github.minimalistic.presentation.features.search.ui.SearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object MainFlow: SupportAppScreen() {
        override fun getFragment(): Fragment = MainFlowFragment.newInstance()
    }

    object SearchScreen: SupportAppScreen() {
        override fun getFragment(): Fragment = SearchFragment.newInstance()
    }

    data class DisplayInfoScreen(val model: UserWithReposModel): SupportAppScreen() {
        override fun getFragment(): Fragment = DisplayUserInfoFragment.newInstance(model)
    }
}