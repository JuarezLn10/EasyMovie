package pe.edu.upc.easymovie.features.favorites.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Preview(showBackground = true)
@Composable
fun FavoritesView(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val movies by viewModel.favorites.collectAsState()

    Column (
        modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(movies) { movie ->
                FavoriteCard (movie, onClick = {}) {
                    viewModel.onDeleteFavorite(movie)
                }
            }
        }
    }
}