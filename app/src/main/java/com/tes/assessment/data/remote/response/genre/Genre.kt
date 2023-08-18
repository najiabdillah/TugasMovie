package com.tes.assessment.data.remote.response.genre


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("com")
    val id: Int,
    @SerializedName("name")
    val name: String
)