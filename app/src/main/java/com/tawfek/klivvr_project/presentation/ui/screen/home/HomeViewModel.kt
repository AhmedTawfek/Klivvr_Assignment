package com.tawfek.klivvr_project.presentation.ui.screen.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfek.klivvr_project.data.city.json_data_source.JsonDataSource
import com.tawfek.klivvr_project.data.city.trie_data_source.Trie
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.presentation.ui.utils.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    context: Application,
    private val jsonDataSource: JsonDataSource = JsonDataSource(),
    private val fileUtils: FileUtils = FileUtils()
) : AndroidViewModel(context) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val trie = Trie()

    init {
        // Running the parsing operation in IO thread, so we don't block the main Thread.
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Search","Parsing json file.")
            val inputStream = fileUtils.openInputStream(context, JsonDataSource.JSON_FILE_NAME)
            val parsedCities = jsonDataSource.parseJsonToCitiesList(inputStream)
            Log.d("Search","Adding parsed objects to the Trie data structure.")
            for (city in parsedCities) {
                trie.insert(city)
            }
            Log.d("Search","Trie data structure is now ready.")
            _uiState.update {
                it.copy(loadingState = LoadingUiState.Idle, cities = parsedCities)
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(loadingState = LoadingUiState.Loading) }

            val cities = trie.search(query)
            _uiState.update {
                it.copy(loadingState = LoadingUiState.Idle, cities = cities, searchQuery = query)
            }
        }
    }

    fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchQueryChanged -> {
                search(event.query)
            }
        }
    }
}

data class HomeUiState(
    val loadingState: LoadingUiState = LoadingUiState.Shimmer,
    val error: String? = null,
    val cities: List<City> = emptyList(),
    val searchQuery: String = ""
)

sealed class HomeEvent {
    data class SearchQueryChanged(val query: String) : HomeEvent()
}

sealed class LoadingUiState{
    object Shimmer : LoadingUiState()
    object Loading : LoadingUiState()
    object Idle : LoadingUiState()
}