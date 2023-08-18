package com.tes.assessment.persentation.detail

import com.tes.assessment.domain.model.trailer.Trailers

interface DetailMovieCallback {
    fun onNavigateYoutube(trailers: Trailers)
}