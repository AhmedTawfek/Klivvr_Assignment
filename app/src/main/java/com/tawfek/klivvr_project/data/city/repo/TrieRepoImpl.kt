package com.tawfek.klivvr_project.data.city.repo

import com.tawfek.klivvr_project.data.city.trie_data_source.Trie
import com.tawfek.klivvr_project.data.mappers.toCity
import com.tawfek.klivvr_project.data.mappers.toCityEntity
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.domain.city.repo.TrieRepo

class TrieRepoImpl(private val trie : Trie) : TrieRepo {
    override fun insert(city: City) {
        trie.insert(city.toCityEntity())
    }
    override fun search(prefix: String): List<City> {
        return trie.search(prefix).map { it.toCity() }
    }
}