package com.tawfek.klivvr_project.domain.city.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id : Int,
    val coordinates: Coordinates,
    val country : String,
    val name: String
)