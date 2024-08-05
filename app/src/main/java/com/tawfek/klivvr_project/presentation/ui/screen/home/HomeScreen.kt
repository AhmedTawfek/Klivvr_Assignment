package com.tawfek.klivvr_project.presentation.ui.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.tawfek.klivvr_project.domain.city.model.City
import com.tawfek.klivvr_project.domain.city.model.Coordinates
import com.tawfek.klivvr_project.presentation.ui.screen.common.ShimmerEffect
import com.tawfek.klivvr_project.presentation.ui.theme.KlivvrProjectTheme
import com.tawfek.klivvr_project.presentation.ui.theme.Typography


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState,
    cityList: List<City>,
    query: (String) -> Unit,
    navigate: (Coordinates) -> Unit
) {
    val context = LocalContext.current

    when (loadingUiState) {
        is LoadingUiState.Shimmer -> {
            // When the application is started for the first time, we will show a shimmer
            // I will show a progress bar ( Spinner ) to simulate if it was a shimmer effect.
            // I will not create a real shimmer effect because no time for that :(
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShimmerEffect(modifier = Modifier)
            }
        }

        else -> {
            CitySearchScreen(
                modifier = modifier,
                loadingUiState = loadingUiState,
                cityList = cityList,
                query = query
            ){coordinatesModel ->
                openGoogleMaps(context = context, latitude = coordinatesModel.latitude, longitude = coordinatesModel.longitude)
                //navigate(coordinatesModel)
            }
        }
    }
}

fun openGoogleMaps(context: Context, latitude: Double, longitude: Double) {
    try {
        val uri = Uri.parse("geo:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        context.startActivity(intent)
    }catch (e:Exception){
        e.printStackTrace()
    }
}

@Composable
fun CitySearchScreen(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState,
    cityList: List<City>,
    query: (String) -> Unit,
    navigate: (Coordinates) -> Unit
) {

    // Using remember saveable to save the state of the text even when orientation changes happens.
    var searchQueryText by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchQueryText,
            onValueChange = {
                searchQueryText = it
                query(it)
            },
            label = { Text("Search for a city") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                if (loadingUiState is LoadingUiState.Loading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = Color.Blue)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            items(cityList) { city ->
                CityCard(city = city){coordinatesModel ->
                    navigate(coordinatesModel)
                }
            }
        }
    }
}

@Composable
fun CityCard(modifier: Modifier = Modifier, city: City,navigate: (Coordinates) -> Unit) {

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        shape = ShapeDefaults.Medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = {
            navigate(city.coordinates)
        }) {
        Column {
            Text(
                text = "${city.name}, ${city.country}",
                modifier = Modifier.padding(10.dp),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Latitude : ${city.coordinates.latitude} , Longitude : ${city.coordinates.longitude}",
                modifier = Modifier.padding(10.dp),
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {

    KlivvrProjectTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            val cityList = listOf(
                City(
                    1,
                    coordinates = Coordinates(latitude = 30.0444, longitude = 31.2357),
                    country = "Egypt",
                    name = "Alexandria"
                ),
                City(
                    2,
                    coordinates = Coordinates(latitude = 30.0444, longitude = 31.2357),
                    country = "Egypt",
                    name = "Cairo"
                ),
                City(
                    3,
                    coordinates = Coordinates(latitude = 30.0444, longitude = 31.2357),
                    country = "Egypt",
                    name = "Giza"
                ),
                City(
                    4,
                    coordinates = Coordinates(latitude = 30.0444, longitude = 31.2357),
                    country = "Egypt",
                    name = "Luxor"
                ),
                City(
                    5,
                    coordinates = Coordinates(latitude = 30.0444, longitude = 31.2357),
                    country = "Egypt",
                    name = "Aswan"
                ),
            )

            HomeScreen(loadingUiState = LoadingUiState.Idle, cityList = cityList, query = {

            }) {

            }
        }
    }
}