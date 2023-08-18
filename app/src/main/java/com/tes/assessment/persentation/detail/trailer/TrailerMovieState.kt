package com.tes.assessment.persentation.detail.trailer

import com.tes.assessment.domain.model.trailer.Trailers

data class TrailerMovieState(
    val isLoading: Boolean = false,
    val trailerList: List<Trailers> = emptyList(),
    val error: String = ""
)