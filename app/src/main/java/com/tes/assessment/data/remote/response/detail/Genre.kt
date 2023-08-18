package com.tes.assessment.data.remote.response.detail


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("com")
    val id: Int,
    @SerializedName("name")
    val name: String
)