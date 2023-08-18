package com.tes.assessment.persentation.award.genre

import com.tes.assessment.domain.model.genre.Genres

data class GenreState(
    val isLoading: Boolean = false,
    val genreList: List<Genres> = emptyList(),
    val error: String = ""
)