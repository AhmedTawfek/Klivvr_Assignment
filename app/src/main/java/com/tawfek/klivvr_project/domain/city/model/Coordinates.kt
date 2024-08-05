package com.tawfek.klivvr_project.domain.city.model

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)