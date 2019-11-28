package com.github.minimalistic.presentation.features.search.pm

import me.dmdev.rxpm.widget.inputControl
import com.github.minimalistic.domain.features.repos.interactor.GetReposByUserUseCase
import com.github.minimalistic.domain.features.search.interactor.GetResultBySearchUseCase
import com.github.minimalistic.domain.features.search.model.SearchModel
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.Screens
import com.github.minimalistic.presentation.core.pm.BasePm
import com.github.minimalistic.presentation.core.pm.ServiceFacade
import com.github.minimalistic.presentation.features.search.model.UserWithReposModel
import javax.inject.Inject

class SearchPm @Inject constructor(
    private val getResultBySearchUseCase: GetResultBySearchUseCase,
    private val getReposByUserUseCase: GetReposByUserUseCase,
    services: ServiceFacade
) : BasePm(services) {

    val searchNameInput = inputControl()
    val searchNameAction = Action<Unit>()
    val buttonVisibilityState = State(false)
    private val getReposAction = Action<SearchModel>()

    override fun onCreate() {
        super.onCreate()

        searchNameInput
            .textChanges
            .observable
            .map(::isValid)
            .doOnNext(::validate)
            .subscribe()
            .untilDestroy()

        getReposAction.observable.flatMapSingle { searchModel ->
            getReposByUserUseCase.execute(searchModel.login)
                .doOnSuccess { listOfRepos ->
                    router.navigateTo(
                        Screens.DisplayInfoScreen(
                            UserWithReposModel(
                                searchModel,
                                listOfRepos
                            )
                        )
                    )
                }
                .doOnError(::handleError)
            }
            .retry()
            .subscribe()
            .untilDestroy()


        searchNameAction
            .observable
            .flatMapSingle {
                getResultBySearchUseCase
                    .execute(searchNameInput.text.value)
                    .bindProgress()
                    .doOnError(::handleError)
                    .doOnSuccess(getReposAction.consumer)
            }
            .retry()
            .subscribe()
            .untilDestroy()
    }

    private fun isValid(textValue: String?) =
        !textValue.isNullOrEmpty()

    private fun validate(isValid: Boolean) {
        buttonVisibilityState.consumer.accept(isValid)
        if (!isValid) {
            searchNameInput.error.consumer.accept(resources.getString(R.string.search_fragment_input_error))
        }
    }
}