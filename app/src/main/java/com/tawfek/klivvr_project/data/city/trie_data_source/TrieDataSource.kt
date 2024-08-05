package com.tawfek.klivvr_project.data.city.trie_data_source

import com.tawfek.klivvr_project.data.city.model.CityEntity
import com.tawfek.klivvr_project.domain.city.model.City

/*
 * The requirements says that we need the search algorithm to be faster than linear
 * I couldn't use a normal hash map which would be O(1) because we need to search for prefixes
 * I searched online for the optimal data structure for this case, and i found that Trie is the optimal for our case.
 * Trie is O(L) and L is the length of the word or prefix.
 */

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isEndOfWord: Boolean = false,
    var cityData: CityEntity? = null
)

class Trie {
    private val root = TrieNode()

    fun insert(city: CityEntity) {
        var node = root
        for (char in city.name.lowercase()) {
            node = node.children.getOrPut(char) { TrieNode() }
        }
        node.isEndOfWord = true
        node.cityData = city
    }

    fun search(prefix: String): List<CityEntity> {
        val results = mutableListOf<CityEntity>()
        var node = root
        for (char in prefix.lowercase()) {
            if (char !in node.children) return results
            node = node.children[char]!!
        }

        // Collect all cities starting from the node corresponding to the prefix
        collectAllCities(node, results)

        return results
    }

    private fun collectAllCities(node: TrieNode, results: MutableList<CityEntity>) {
        if (node.isEndOfWord) {
            node.cityData?.let { results.add(it) }
        }
        for ((_, child) in node.children) {
            collectAllCities(child, results)
        }
    }
}