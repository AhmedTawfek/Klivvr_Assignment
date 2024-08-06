package com.tawfek.klivvr_project.data.city.json_data_source

import com.google.gson.Gson
import com.tawfek.klivvr_project.data.city.model.CityEntity
import com.tawfek.klivvr_project.domain.city.model.City
import java.io.InputStream
import javax.inject.Inject

 /*
 * Usually in the data package we have our data sources which could be [ Remote, Local, etc. ]
 * Our data source here is from a json file, so i called it a json_data_source.
 */
class JsonDataSource{

    suspend fun parseJsonToCitiesList(inputStream: InputStream): List<CityEntity> {
        /* Using Gson from google to parse the json file.
        * Why Gson?
        * Because i made a compression between Gson, Kotlinx.serialization and Moshi.
        * Gson was the fastest.
        * Gson takes less than 2 seconds.
        * Kotlinx takes more than 3 seconds
        * Moshi takes around 4 seconds.
         */
        val json = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(json, Array<CityEntity>::class.java).toList()
    }

    companion object{
        const val JSON_FILE_NAME = "cities.json"
    }
}