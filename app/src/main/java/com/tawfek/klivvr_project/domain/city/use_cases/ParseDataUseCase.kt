package com.tawfek.klivvr_project.domain.city.use_cases

import com.tawfek.klivvr_project.domain.city.repo.JsonRepo
import java.io.InputStream

class ParseDataUseCase(private val jsonRepo: JsonRepo) {
    suspend operator fun invoke(inputStream: InputStream) = jsonRepo.parseJsonToCitiesList(inputStream)
}