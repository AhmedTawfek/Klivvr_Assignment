package com.tawfek.klivvr_project.presentation.ui.di

import com.tawfek.klivvr_project.domain.city.repo.JsonRepo
import com.tawfek.klivvr_project.domain.city.repo.TrieRepo
import com.tawfek.klivvr_project.domain.city.use_cases.ParseDataUseCase
import com.tawfek.klivvr_project.domain.city.use_cases.SortListUseCase
import com.tawfek.klivvr_project.domain.city.use_cases.TrieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesParseDataUseCase(jsonRepo: JsonRepo): ParseDataUseCase {
        return ParseDataUseCase(jsonRepo)
    }

    @Provides
    fun providesTrieUseCases(trieRepo: TrieRepo, sortListUseCase: SortListUseCase): TrieUseCases {
        return TrieUseCases(trieRepo,sortListUseCase)
    }

    @Provides
    fun providesSortListUseCase(): SortListUseCase {
        return SortListUseCase()
    }
}