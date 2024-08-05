package com.tawfek.klivvr_project.data.city.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CityEntity(
    @SerializedName("_id") val id : Int,
    @SerializedName("coord") val coordinates: CoordinatesEntity,
    val country : String,
    val name: String
)