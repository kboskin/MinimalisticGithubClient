package com.github.minimalistic.data.features.search.api

import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleJust
import com.github.minimalistic.data.features.search.dto.SearchResultDto

@Suppress("MagicNumber")
class MockedSearchApi : SearchApi{
    override fun getUserByName(name: String): Single<SearchResultDto> = SingleJust<SearchResultDto>(
        SearchResultDto(
            "start_coder_007",
            "12345",
            "https://i.dlpng.com/static/png/360429_preview.png",
            20,
            25,
            "Star",
            "Vasiliy Poopkin",
            "https://api.github.com/users/start_coder_007/repos"
        )
    )

}