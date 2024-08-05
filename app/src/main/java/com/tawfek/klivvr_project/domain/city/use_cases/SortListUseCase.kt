package com.tawfek.klivvr_project.domain.city.use_cases

import com.tawfek.klivvr_project.domain.city.model.City

class SortListUseCase {
     operator fun invoke(list: List<City>): List<City> {
        /*
        * The requirements says that the list should be sorted alphabetically, city name then country, and case is insensitive.
        */
        return list.sortedWith(compareBy<City> { it.name.lowercase() }.thenBy { it.country.lowercase() })
    }
}