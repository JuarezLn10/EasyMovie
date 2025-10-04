package pe.edu.upc.easymovie.features.movies.domain.repositories

import pe.edu.upc.easymovie.features.movies.domain.models.Movie

interface MovieRepository {
    suspend fun getAllMovies(): List<Movie>
}