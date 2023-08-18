package com.tes.assessment.persentation.dashboard

import com.tes.assessment.domain.model.movie.Movies

interface DashboardCallback {
    fun onDetailMovie(movie: Movies)
}