package com.tawfek.klivvr_project.domain.city.repo

import com.tawfek.klivvr_project.domain.city.model.City
import java.io.InputStream

/*
 * Why did i create 2 interfaces one for json and another for Trie and not just a single one?
 * Because if i did this i would violate the Single Responsibility Principle.
 */
interface JsonRepo{
    suspend fun parseJsonToCitiesList(inputStream : InputStream) : List<City>
}