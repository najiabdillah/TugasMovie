package com.tes.assessment.persentation.dashboard.upcoming

import com.tes.assessment.domain.model.movie.Movies

data class UpcomingState(
    val isLoading: Boolean = false,
    val upcomingList: List<Movies> = emptyList(),
    val error: String = ""
)