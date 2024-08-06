package com.tawfek.klivvr_project.domain.city.repo

import com.tawfek.klivvr_project.domain.city.model.City

/*
 * Why did i create 2 interfaces one for json and another for Trie and not just a single one?
 * Because if i did this i would violate the Single Responsibility Principle.
 */
interface TrieRepo {
    fun insert(city: City)
    fun search(prefix: String): List<City>
}