package com.github.minimalistic.data.features.repos.api

import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleJust
import com.github.minimalistic.data.features.repos.dto.ReposResultDto

@Suppress("MagicNumber")
class MockedReposApi : ReposApi {
    override fun getUserByName(name: String): Single<List<ReposResultDto>> = SingleJust<List<ReposResultDto>>(
        listOf(ReposResultDto(
            "random_repo1",
            "random_description1",
            0,
            1,
            "Kotlin"
        ),
            ReposResultDto(
                "random_repo2",
                "random_description2",
                43,
                23,
                "Kotlin"
            )
        )
    )

}