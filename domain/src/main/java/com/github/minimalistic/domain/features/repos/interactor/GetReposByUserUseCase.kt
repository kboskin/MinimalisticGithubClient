package com.github.minimalistic.domain.features.repos.interactor

import com.nullgr.core.interactor.SingleUseCase
import com.nullgr.core.rx.schedulers.SchedulersFacade
import io.reactivex.Single
import com.github.minimalistic.domain.features.repos.model.RepoModel
import com.github.minimalistic.domain.features.repos.repository.ReposRepository
import javax.inject.Inject

class GetReposByUserUseCase @Inject constructor(
    private val repository: ReposRepository,
    schedulersFacade: SchedulersFacade
) : SingleUseCase<List<RepoModel>, String>(schedulersFacade) {

    override fun buildUseCaseObservable(params: String?): Single<List<RepoModel>> =
        with(checkNotNull(params)) {
            repository.getUserRepos(this)
        }
}