package com.github.minimalistic.presentation.features.dispay_user_info.pm

import com.github.minimalistic.presentation.R.string.user_info_fragment_followers
import com.github.minimalistic.presentation.R.string.user_info_fragment_following
import com.github.minimalistic.presentation.core.pm.BaseListPm
import com.github.minimalistic.presentation.core.pm.ServiceFacade
import com.github.minimalistic.presentation.core.ui.state_view.StateData
import com.github.minimalistic.presentation.core.ui.states.States
import com.github.minimalistic.presentation.features.dispay_user_info.mapper.UserInfoUIMapper
import com.github.minimalistic.presentation.features.search.model.UserWithReposModel
import javax.inject.Inject

class DisplayUserInfoPm @Inject constructor(
    private val itemsMapper: UserInfoUIMapper,
    services: ServiceFacade

) : BaseListPm(services) {

    private val userWithReposAcceptedModel = State<UserWithReposModel>()
    val displayUserImageCommand = Command<String>()
    val displayUserFollowers = Command<String>()
    val displayUserFollowing = Command<String>()
    val displayUserNameAndCompany = Command<String>()

    override fun onCreate() {
        super.onCreate()

        passToEmptyStateContainer(States.CompareListEmptyState(resources))
    }

    override fun onBind() {
        super.onBind()

        userWithReposAcceptedModel.observable
            .doOnNext {
                items.consumer.accept(itemsMapper.mapFromObject(it.repos))

                displayUserNameAndCompany.consumer.accept(("${it.searchModel.name}, ${it.searchModel.company}"))
                displayUserFollowers.consumer.accept(
                    resources.getString(user_info_fragment_followers) +
                        it.searchModel.followersCount.toString()
                )
                displayUserFollowing.consumer.accept(
                    resources.getString(user_info_fragment_following) +
                        it.searchModel.followingCount.toString()
                )
                displayUserImageCommand.consumer.accept(it.searchModel.photoUrl)
            }
            .subscribe()
            .untilUnbind()
    }

    fun passModelToState(model: UserWithReposModel) {
        userWithReposAcceptedModel.consumer.accept(model)
    }
}
