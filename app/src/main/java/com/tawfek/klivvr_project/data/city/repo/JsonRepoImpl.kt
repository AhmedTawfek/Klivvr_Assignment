package com.tawfek.klivvr_project.data.city.repo

import com.tawfek.klivvr_project.data.city.json_data_source.JsonDataSource
import com.tawfek.klivvr_project.data.city.model.CityEntity
import com.tawfek.klivvr_project.data.mappers.toCity
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.domain.city.repo.JsonRepo
import java.io.InputStream

class JsonRepoImpl(private val jsonDataSource: JsonDataSource) : JsonRepo {

    override suspend fun parseJsonToCitiesList(inputStream: InputStream): List<City> {
        return jsonDataSource.parseJsonToCitiesList(inputStream).map { it.toCity() }
    }

}