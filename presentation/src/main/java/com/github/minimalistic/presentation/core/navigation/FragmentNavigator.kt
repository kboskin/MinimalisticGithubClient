package com.github.minimalistic.presentation.core.navigation

import androidx.fragment.app.Fragment
import com.github.minimalistic.presentation.R

open class FragmentNavigator(
    fragment: Fragment
) : ExtendedNavigator(fragment.activity, fragment.childFragmentManager, R.id.containerView)