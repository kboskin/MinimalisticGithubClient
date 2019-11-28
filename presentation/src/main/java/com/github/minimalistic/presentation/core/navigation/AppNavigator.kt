package com.github.minimalistic.presentation.core.navigation

import androidx.fragment.app.FragmentActivity
import com.github.minimalistic.presentation.R

class AppNavigator(
    activity: FragmentActivity
) : ExtendedNavigator(activity, activity.supportFragmentManager, R.id.containerView)