package com.tawfek.klivvr_project.presentation.ui.screen.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.tawfek.klivvr_project.domain.city.model.Coordinates
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeEvent.SearchQueryChanged
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeScreen
import com.tawfek.klivvr_project.presentation.ui.screen.home.HomeViewModel

@Composable
fun Navigation(navHostController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navHostController, startDestination = Home) {

        composable<Home> {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val uiState = homeViewModel.uiState.collectAsState()

            HomeScreen(
                modifier = modifier,
                loadingUiState = uiState.value.loadingState,
                cityList = uiState.value.cities,
                query = { searchQuery ->
                    homeViewModel.handleEvents(SearchQueryChanged(searchQuery))
                }) { coordinatesModel ->
                navHostController.navigate(coordinatesModel)
            }
        }
    }
}