package com.tawfek.klivvr_project.data.city.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesEntity(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)