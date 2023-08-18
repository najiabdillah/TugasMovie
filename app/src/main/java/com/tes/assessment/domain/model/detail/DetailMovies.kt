package com.tes.assessment.domain.model.detail

import com.tes.assessment.data.remote.response.detail.Genre
import com.tes.assessment.data.remote.response.detail.ProductionCompany
import com.tes.assessment.data.remote.response.detail.ProductionCountry
import com.tes.assessment.data.remote.response.detail.SpokenLanguage

data class DetailMovies(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
