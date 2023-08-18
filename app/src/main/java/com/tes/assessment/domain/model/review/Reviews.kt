package com.tes.assessment.domain.model.review

import com.tes.assessment.data.remote.response.review.AuthorDetails

data class Reviews(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)
