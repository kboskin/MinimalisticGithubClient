package com.github.minimalistic.presentation.core.navigation

import com.github.minimalistic.presentation.core.navigation.commands.AddTabs
import com.github.minimalistic.presentation.core.navigation.commands.AttachTab
import com.github.minimalistic.presentation.core.navigation.commands.ClearTabStack
import com.github.minimalistic.presentation.core.navigation.commands.ShowBottomSheet
import com.github.minimalistic.presentation.core.navigation.screens.BottomSheetScreen
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FlowRouter(private val parentRouter: Router?) : UiThreadRouter() {

    fun startFlow(screen: SupportAppScreen) {
        runCommand { navigateTo(screen) }
    }

    fun newRootFlow(screen: SupportAppScreen) {
        runCommand { newRootScreen(screen) }
    }

    fun finishFlow() {
        runCommand { exit() }
    }

    fun newTabs(screens: Array<SupportAppScreen>) {
        executeCommands(AddTabs(screens))
    }

    fun navigateToTab(screen: SupportAppScreen) {
        executeCommands(AttachTab(screen))
    }

    fun clearTabStack(screen: SupportAppScreen) {
        executeCommands(ClearTabStack(screen))
    }

    fun showBottomSheet(screen: BottomSheetScreen) {
        executeCommands(ShowBottomSheet(screen))
    }

    private fun runCommand(command: Router.() -> Unit) {
        if (parentRouter != null)
            parentRouter.command()
        else
            this.command()
    }
}
