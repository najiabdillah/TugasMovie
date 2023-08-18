package com.tes.assessment.data

import com.tes.assessment.data.remote.response.credit.Cast
import com.tes.assessment.data.remote.response.detail.DetailMovieResponse
import com.tes.assessment.data.remote.response.genre.Genre
import com.tes.assessment.domain.model.credit.Credits
import com.tes.assessment.domain.model.detail.DetailMovies
import com.tes.assessment.domain.model.genre.Genres
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.domain.model.review.Reviews
import com.tes.assessment.domain.model.trailer.Trailers
import com.tes.assessment.utils.orFalse
import com.tes.assessment.utils.orNol
import com.tes.assessment.data.remote.response.review.Result

fun List<Genre>?.mappingGenreToUseCaseEntity(): List<Genres> {
    val newList: MutableList<Genres> = mutableListOf()

    this?.forEach {
        newList.add(
            Genres(
                id = it.id.orNol(),
                name = it.name.orEmpty()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<com.tes.assessment.data.remote.response.movie.Result>?.mappingPopularMovieToUseCaseEntity(): List<Movies> {
    val newList: MutableList<Movies> = mutableListOf()

    this?.forEach {
        newList.add(
            Movies(
                id = it.id.orNol(),
                adult = it.adult.orFalse(),
                backdropPath = it.backdropPath.orEmpty(),
                genreIds = it.genreIds.orEmpty(),
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                overview = it.overview.orEmpty(),
                popularity = it.popularity.orNol(),
                posterPath = it.posterPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                title = it.title.orEmpty(),
                video = it.video.orFalse(),
                voteAverage = it.voteAverage.orNol(),
                voteCount = it.voteCount.orNol()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<com.tes.assessment.data.remote.response.movie.Result>?.mappingUpcomingMovieToUseCaseEntity(): List<Movies> {
    val newList: MutableList<Movies> = mutableListOf()

    this?.forEach {
        newList.add(
            Movies(
                id = it.id.orNol(),
                adult = it.adult.orFalse(),
                backdropPath = it.backdropPath.orEmpty(),
                genreIds = it.genreIds.orEmpty(),
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                overview = it.overview.orEmpty(),
                popularity = it.popularity.orNol(),
                posterPath = it.posterPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                title = it.title.orEmpty(),
                video = it.video.orFalse(),
                voteAverage = it.voteAverage.orNol(),
                voteCount = it.voteCount.orNol()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<com.tes.assessment.data.remote.response.movie.Result>?.mappingGenreMovieToUseCaseEntity(): List<Movies> {
    val newList: MutableList<Movies> = mutableListOf()

    this?.forEach {
        newList.add(
            Movies(
                id = it.id.orNol(),
                adult = it.adult.orFalse(),
                backdropPath = it.backdropPath.orEmpty(),
                genreIds = it.genreIds.orEmpty(),
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                overview = it.overview.orEmpty(),
                popularity = it.popularity.orNol(),
                posterPath = it.posterPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                title = it.title.orEmpty(),
                video = it.video.orFalse(),
                voteAverage = it.voteAverage.orNol(),
                voteCount = it.voteCount.orNol()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<com.tes.assessment.data.remote.response.movie.Result>?.mappingSearchMovieToUseCaseEntity(): List<Movies> {
    val newList: MutableList<Movies> = mutableListOf()

    this?.forEach {
        newList.add(
            Movies(
                id = it.id.orNol(),
                adult = it.adult.orFalse(),
                backdropPath = it.backdropPath.orEmpty(),
                genreIds = it.genreIds.orEmpty(),
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                overview = it.overview.orEmpty(),
                popularity = it.popularity.orNol(),
                posterPath = it.posterPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                title = it.title.orEmpty(),
                video = it.video.orFalse(),
                voteAverage = it.voteAverage.orNol(),
                voteCount = it.voteCount.orNol()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<Result>?.mappingReviewToUseCaseEntity(): List<Reviews> {
    val newList: MutableList<Reviews> = mutableListOf()

    this?.forEach {
        newList.add(
            Reviews(
                id = it.id.orEmpty(),
                author = it.author.orEmpty(),
                authorDetails = it.authorDetails,
                content = it.content.orEmpty(),
                createdAt = it.createdAt.orEmpty(),
                updatedAt = it.updatedAt.orEmpty(),
                url = it.url.orEmpty()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun List<com.tes.assessment.data.remote.response.trailer.Result>?.mappingTrailerToUseCaseEntity(): List<Trailers> {
    val newList: MutableList<Trailers> = mutableListOf()

    this?.forEach {
        newList.add(
            Trailers(
                id = it.id.orEmpty(),
                iso31661 = it.iso31661.orEmpty(),
                iso6391 = it.iso6391.orEmpty(),
                key = it.key.orEmpty(),
                name = it.name.orEmpty(),
                official = it.official.orFalse(),
                publishedAt = it.publishedAt.orEmpty(),
                site = it.site.orEmpty(),
                size = it.size.orNol(),
                type = it.type.orEmpty()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun DetailMovieResponse.mappingDetailMovieToUseCaseEntity(): List<DetailMovies> {
    val newList: MutableList<DetailMovies> = mutableListOf()

    newList.add(
        DetailMovies(
            adult = adult.orFalse(),
            backdropPath = backdropPath.orEmpty(),
            budget = budget.orNol(),
            genres = genres.orEmpty(),
            homepage = homepage.orEmpty(),
            id = id.orNol(),
            imdbId = imdbId.orEmpty(),
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            overview = overview.orEmpty(),
            popularity = popularity.orNol(),
            posterPath = posterPath.orEmpty(),
            productionCompanies = productionCompanies.orEmpty(),
            productionCountries = productionCountries.orEmpty(),
            releaseDate = releaseDate.orEmpty(),
            revenue = revenue.orNol(),
            runtime = runtime.orNol(),
            spokenLanguages = spokenLanguages.orEmpty(),
            status = status.orEmpty(),
            tagline = tagline.orEmpty(),
            title = title.orEmpty(),
            video = video.orFalse(),
            voteAverage = voteAverage.orNol(),
            voteCount = voteCount.orNol()
        )
    )

    return newList
}

fun List<Cast>?.mappingCreditToUseCaseEntity(): List<Credits> {
    val newList: MutableList<Credits> = mutableListOf()

    this?.forEach {
        newList.add(
            Credits(
                adult = it.adult.orFalse(),
                castId = it.castId.orNol(),
                character = it.character.orEmpty(),
                creditId = it.creditId.orEmpty(),
                gender = it.gender.orNol(),
                id = it.id.orNol(),
                knownForDepartment = it.knownForDepartment.orEmpty(),
                name = it.name.orEmpty(),
                order = it.order.orNol(),
                originalName = it.originalName.orEmpty(),
                popularity = it.popularity.orNol(),
                profilePath = it.profilePath.orEmpty()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}
