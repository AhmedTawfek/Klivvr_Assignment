package com.tawfek.klivvr_project.presentation.ui.screen.navigation

import kotlinx.serialization.Serializable

interface NavigationScreen {
    val route : String
}
/*
 * We were navigating between each screen by using the route which is a string.
 * And passing data between screens was a hard job to do.
 * Now, we have a type safety navigation, and we need this @Serializable annotation
 * So, for the data that we will pass we just add this annotation on top of it, and that's all.
 */
@Serializable
object Home : NavigationScreen {
    override val route : String
        get() = "home_screen"
}

@Serializable
object Maps : NavigationScreen {
    override val route : String
        get() = "maps_screen"
}