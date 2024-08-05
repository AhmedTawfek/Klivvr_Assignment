package com.tawfek.klivvr_project.presentation.ui.screen.main_activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tawfek.klivvr_project.R
import com.tawfek.klivvr_project.data.city.json_data_source.JsonDataSource
import com.tawfek.klivvr_project.data.city.trie_data_source.Trie
import com.tawfek.klivvr_project.data.city.trie_data_source.TrieDataStructure
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeEvent
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeEvent.SearchQueryChanged
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeScreen
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeViewModel
import com.tawfek.klivvr_project.presentation.ui.theme.KlivvrProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.InputStream
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KlivvrProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val uiState = homeViewModel.uiState.collectAsState()

                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        loadingUiState = uiState.value.loadingState,
                        cityList = uiState.value.cities,
                        search = uiState.value.searchQuery
                    ) { searchQuery ->
                        homeViewModel.handleEvents(SearchQueryChanged(searchQuery))
                    }
                }
            }
        }
    }
}

@Composable
fun CitySearchScreen(
    modifier: Modifier = Modifier,
    cityList: List<City>,
    search: (String) -> Unit
) {

    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = query) {
        search(query)
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            label = { Text("Search for a city") }
        )
        LazyColumn {
            items(cityList) { city ->
                Text(
                    "${city.name}, ${city.country}, ${city.id}, ${city.coordinates}",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KlivvrProjectTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //CitySearchScreen(Trie())
}