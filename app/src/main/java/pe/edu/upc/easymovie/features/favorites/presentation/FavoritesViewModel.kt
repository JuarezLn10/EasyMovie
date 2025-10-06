package pe.edu.upc.easymovie.features.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.easymovie.features.movies.domain.models.Movie
import pe.edu.upc.easymovie.features.movies.domain.repositories.MovieRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor (private val repository: MovieRepository): ViewModel() {

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    val favorites: StateFlow<List<Movie>> = _favorites

    fun getAllFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
        }
    }

    init {
        getAllFavorites()
    }

    fun onDeleteFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.deleteFavorite(movie)
            getAllFavorites()
        }
    }
}