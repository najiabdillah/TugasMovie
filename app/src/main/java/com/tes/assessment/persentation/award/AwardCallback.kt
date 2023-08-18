package com.tes.assessment.persentation.award

import com.tes.assessment.domain.model.genre.Genres
import com.tes.assessment.domain.model.movie.Movies

interface AwardCallback {
    fun onDetailAwardMovie(movie: Movies)
    fun onGenreMovieId(movieId: Genres)
}