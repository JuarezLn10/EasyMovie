package pe.edu.upc.easymovie.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.easymovie.features.favorites.presentation.FavoritesView
import pe.edu.upc.easymovie.features.movies.presentation.MoviesView

@Composable
fun MainView(modifier: Modifier = Modifier) {

    val navigationItems = listOf(
        NavigationItem("Movies", Icons.Default.Movie, "movies"),
        NavigationItem("Favorites", Icons.Default.Favorite, "favorites")
    )

    val selectedItem = rememberSaveable {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItem.intValue,
                        onClick = {
                            selectedItem.intValue = index
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "movies",
            modifier = modifier.padding(paddingValues)
        ) {
            composable ("movies") {
                MoviesView()
            }

            composable ("favorites") {
                FavoritesView()
            }
        }
    }
}