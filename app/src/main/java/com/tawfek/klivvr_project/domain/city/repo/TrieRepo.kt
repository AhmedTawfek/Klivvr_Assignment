package com.tawfek.klivvr_project.domain.city.repo

import com.tawfek.klivvr_project.domain.city.model.City

interface TrieRepo {
    fun insert(city: City)
    fun search(prefix: String): List<City>
}