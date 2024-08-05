package com.tawfek.klivvr_project.domain.city.repo

import com.tawfek.klivvr_project.domain.city.model.City
import java.io.InputStream

interface JsonRepo{
    suspend fun parseJsonToCitiesList(inputStream : InputStream) : List<City>
}