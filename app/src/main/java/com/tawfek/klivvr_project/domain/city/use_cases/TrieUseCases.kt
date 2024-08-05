package com.tawfek.klivvr_project.domain.city.use_cases

import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.domain.city.repo.TrieRepo

/*
 * Well, i don't like to put every use case in a separate file
 * So, if the functions are related to each other it's better for me to put them in the same file.
 */
class TrieUseCases(private val trioRepo: TrieRepo,private val sortListUseCase: SortListUseCase) {
    fun insertUseCase(city: City) = trioRepo.insert(city)

    fun searchUseCase(prefix: String): List<City> {
        val sortedList = trioRepo.search(prefix)
        return sortListUseCase(sortedList)
    }
}