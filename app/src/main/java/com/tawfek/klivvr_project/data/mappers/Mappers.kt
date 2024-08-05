package com.tawfek.klivvr_project.data.mappers

import com.tawfek.klivvr_project.data.city.model.CityEntity
import com.tawfek.klivvr_project.data.city.model.CoordinatesEntity
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.domain.city.model.Coordinates

fun CityEntity.toCity(): City {
    return City(
        id = this.id,
        coordinates = this.coordinates.toCoordinates(),
        country = this.country,
        name = this.name)
}

fun CoordinatesEntity.toCoordinates(): Coordinates {
    return Coordinates(
        latitude = this.latitude,
        longitude = this.longitude)
}

fun City.toCityEntity(): CityEntity {
    return CityEntity(
        id = this.id,
        coordinates = this.coordinates.toCoordinatesEntity(),
        country = this.country,
        name = this.name)
}

fun Coordinates.toCoordinatesEntity(): CoordinatesEntity {
    return CoordinatesEntity(
        latitude = this.latitude,
        longitude = this.longitude)
}