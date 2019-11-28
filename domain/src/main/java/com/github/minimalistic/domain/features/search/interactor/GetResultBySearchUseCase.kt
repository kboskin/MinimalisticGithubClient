package com.github.minimalistic.domain.features.search.interactor

import com.nullgr.core.interactor.SingleUseCase
import com.nullgr.core.rx.schedulers.SchedulersFacade
import io.reactivex.Single
import com.github.minimalistic.domain.features.search.model.SearchModel
import com.github.minimalistic.domain.features.search.repository.SearchRepository
import javax.inject.Inject

class GetResultBySearchUseCase @Inject constructor(
    private val repository: SearchRepository,
    schedulersFacade: SchedulersFacade
) : SingleUseCase<SearchModel, String>(schedulersFacade) {
    override fun buildUseCaseObservable(params: String?): Single<SearchModel> =
        with(checkNotNull(params)) {
            repository.getUserByName(this)
        }
}