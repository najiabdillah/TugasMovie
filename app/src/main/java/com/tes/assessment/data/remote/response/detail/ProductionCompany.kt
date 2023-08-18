package com.tes.assessment.data.remote.response.detail


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("com")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)