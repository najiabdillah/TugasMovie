package com.tes.assessment.data.remote.response.credit


import com.google.gson.annotations.SerializedName

data class CreditMovieResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("com")
    val id: Int
)