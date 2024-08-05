package com.tawfek.klivvr_project.presentation.ui.di

import com.tawfek.klivvr_project.data.city.json_data_source.JsonDataSource
import com.tawfek.klivvr_project.data.city.repo.JsonRepoImpl
import com.tawfek.klivvr_project.data.city.repo.TrieRepoImpl
import com.tawfek.klivvr_project.data.city.trie_data_source.Trie
import com.tawfek.klivvr_project.domain.city.repo.JsonRepo
import com.tawfek.klivvr_project.domain.city.repo.TrieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun providesJsonRepo(jsonDataSource: JsonDataSource): JsonRepo {
        return JsonRepoImpl(jsonDataSource)
    }

    @Provides
    fun providesTrieRepo(trie: Trie): TrieRepo {
        return TrieRepoImpl(trie)
    }
}