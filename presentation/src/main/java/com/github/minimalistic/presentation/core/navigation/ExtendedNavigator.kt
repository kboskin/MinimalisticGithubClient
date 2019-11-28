package com.github.minimalistic.presentation.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.nullgr.core.ui.fragments.clearBackStack
import com.nullgr.core.ui.fragments.showDialog
import com.github.minimalistic.presentation.core.navigation.commands.AddTabs
import com.github.minimalistic.presentation.core.navigation.commands.AttachTab
import com.github.minimalistic.presentation.core.navigation.commands.ClearTabStack
import com.github.minimalistic.presentation.core.navigation.commands.ShowBottomSheet
import com.github.minimalistic.presentation.core.navigation.screens.BottomSheetScreen
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

open class ExtendedNavigator(
    activity: FragmentActivity?,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    private val tabsHolder = mutableMapOf<Screen, Fragment>()

    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.apply {
            setReorderingAllowed(true)
        }
    }

    override fun applyCommand(command: Command) {
        when (checkCondition(command)) {
            is AttachTab -> attachTabFragment((command as AttachTab).screen)
            is AddTabs -> addTabFragments((command as AddTabs).screens)
            is ClearTabStack -> clearTabStack((command as ClearTabStack).screen)
            is ShowBottomSheet -> showBottomSheet((command as ShowBottomSheet).screen)
            else -> super.applyCommand(command)
        }
    }

    private fun attachTabFragment(screen: SupportAppScreen) {
        fragmentManager.beginTransaction().apply {
            tabsHolder.forEach {
                when (screen) {
                    it.key -> attach(it.value)
                    else -> detach(it.value)
                }
            }
        }.commitNow()
    }

    private fun showBottomSheet(screen: BottomSheetScreen) {
        fragmentManager.showDialog(screen.fragment)
    }

    private fun clearTabStack(screen: SupportAppScreen) {
        tabsHolder[screen]?.childFragmentManager?.clearBackStack()
    }

    private fun addTabFragments(screens: Array<SupportAppScreen>) {
        tabsHolder.clear()
        screens.forEach {
            tabsHolder[it] = fragmentManager.initializeSingleTab(
                it.fragment,
                containerId,
                it.screenKey
            )
        }
    }

    private fun FragmentManager.initializeSingleTab(
        fragment: Fragment,
        containerId: Int,
        tag: String
    ): Fragment =
        findFragmentByTag(tag) ?: fragment.apply {
            beginTransaction()
                .add(containerId, this, tag)
                .detach(this)
                .commitNow()
        }

    private fun checkCondition(command: Command): Command {
        when (command) {
            is AttachTab ->
                if (command.screen.fragment == null) throwInvalidConditionException(command)
            is AddTabs ->
                command.screens.forEach {
                    if (it.fragment == null) throwInvalidConditionException(command)
                }
            is ClearTabStack ->
                if (command.screen.fragment == null) throwInvalidConditionException(command)
        }
        return command
    }

    private fun throwInvalidConditionException(command: Command) {
        throw IllegalStateException(
            "${command.javaClass.simpleName} command supports only fragments"
        )
    }
}