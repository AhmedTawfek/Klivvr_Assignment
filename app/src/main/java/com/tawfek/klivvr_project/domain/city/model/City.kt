package com.tawfek.klivvr_project.domain.city.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class City(
    @SerializedName("_id") val id : Int,
    @SerializedName("coord") val coordinates: Coordinates,
    val country : String,
    val name: String
)

data class Country(
    val name: String
)

data class Coordinates(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)