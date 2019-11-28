package com.github.minimalistic.presentation.features.dispay_user_info.ui

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.widget.text
import com.nullgr.core.ui.extensions.toggleView
import kotlinx.android.synthetic.main.fragment_display_info.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.ui.fragment.BaseListFragment
import com.github.minimalistic.presentation.core.ui.system_ui.StatusBarConfigProvider
import com.github.minimalistic.presentation.core.ui.system_ui.TransparentStatusBarConfigProvider
import com.github.minimalistic.presentation.features.dispay_user_info.pm.DisplayUserInfoPm
import com.github.minimalistic.presentation.features.search.model.UserWithReposModel
import com.github.minimalistic.presentation.utils.bundle
import com.github.minimalistic.presentation.utils.loadImage
import com.github.minimalistic.presentation.utils.visibility

class DisplayUserInfoFragment : BaseListFragment<DisplayUserInfoPm>() {
    override val screenLayout: Int = R.layout.fragment_display_info
    override val classToken: Class<DisplayUserInfoPm> = DisplayUserInfoPm::class.java
    override val statusBarConfigProvider: StatusBarConfigProvider? = TransparentStatusBarConfigProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarActionButtonView.setImageResource(R.drawable.img_github)
        toolbarActionButtonView.toggleView(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true;
        super.onCreate(savedInstanceState)

        arguments?.get(FULL_MODEL)?.let {

            val model = it as UserWithReposModel
            presentationModel.passModelToState(model)
        }
    }

    override fun onBindPresentationModel(pm: DisplayUserInfoPm) {
        super.onBindPresentationModel(pm)

        pm.progressState.bindTo(progressDialog.visibility(childFragmentManager))
        pm.displayUserImageCommand.bindTo{
            profileImage.loadImage(it, R.drawable.item_rotate, R.drawable.img_github_octocat)
        }

        pm.displayUserFollowers.bindTo(followersCountTextView.text())
        pm.displayUserFollowing.bindTo(followingsCountTextView.text())

        pm.displayUserNameAndCompany.bindTo(userNameCompanyTextView.text())
    }

    companion object {
        private const val FULL_MODEL = "full_model"

        fun newInstance(model: UserWithReposModel): DisplayUserInfoFragment = DisplayUserInfoFragment().apply {
            this.arguments = bundle(FULL_MODEL to (model))
        }
    }
}