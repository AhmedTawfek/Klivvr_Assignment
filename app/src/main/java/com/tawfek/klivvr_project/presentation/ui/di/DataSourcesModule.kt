package com.tawfek.klivvr_project.presentation.ui.di

import com.tawfek.klivvr_project.data.city.json_data_source.JsonDataSource
import com.tawfek.klivvr_project.data.city.trie_data_source.Trie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {
    @Provides
    fun providesTrieDataSource(): Trie {
        return Trie()
    }

    @Provides
    fun providesJsonDataSource(): JsonDataSource {
        return JsonDataSource()
    }
}