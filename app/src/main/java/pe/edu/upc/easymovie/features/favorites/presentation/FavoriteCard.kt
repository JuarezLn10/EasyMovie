package pe.edu.upc.easymovie.features.favorites.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.easymovie.features.movies.domain.models.Movie

@Composable
fun FavoriteCard(
    movie: Movie,
    onClick: () -> Unit,
    onDeleteFavorite: () -> Unit
) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .height(96.dp)
                    .width(56.dp),
                contentScale = ContentScale.Fit
            )

            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    movie.title, maxLines = 1,
                    fontWeight = FontWeight.SemiBold
                )
                Text(movie.overview, maxLines = 2)
            }

            IconButton(
                onClick = onDeleteFavorite

            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}