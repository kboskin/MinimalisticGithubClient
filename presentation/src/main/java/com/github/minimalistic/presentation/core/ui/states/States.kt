package com.github.minimalistic.presentation.core.ui.states

import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.ui.state_view.StateData
import com.nullgr.core.resources.ResourceProvider

sealed class States : StateData {

    data class CompareListEmptyState(
        val resources: ResourceProvider,
        override val icon: Int? = R.drawable.img_github_octocat,
        override val title: String? = resources.getString(R.string.fragment_user_info_empty_state_title),
        override val description: String? = resources.getString(R.string.fragment_user_info_empty_state_description),
        override val button: String? = null
    ) : States()
}