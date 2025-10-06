package pe.edu.upc.easymovie.features.movies.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.easymovie.features.movies.data.local.dao.MovieDao
import pe.edu.upc.easymovie.features.movies.data.local.models.MovieEntity
import pe.edu.upc.easymovie.features.movies.data.remote.services.MovieService
import pe.edu.upc.easymovie.features.movies.domain.models.Movie
import pe.edu.upc.easymovie.features.movies.domain.repositories.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor (
    private val service: MovieService,
    private val movieDao: MovieDao

): MovieRepository {
    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        try {
            val response = service.getAllMovies()
            if (response.isSuccessful) {
                response.body()?.let { wrapperDto ->
                    return@withContext wrapperDto.movies.map { movieDto ->
                        Movie(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            posterPath = "https://image.tmdb.org/t/p/w500${movieDto.posterPath}",
                            isFavorite = movieDao.fetchById(movieDto.id).isNotEmpty()
                        )
                    }
                }
            }
        }
        catch (e: Exception) {
            return@withContext emptyList()
        }

        return@withContext emptyList()
    }

    override suspend fun insertFavorite(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insert(MovieEntity(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath
        ))
    }

    override suspend fun deleteFavorite(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.delete(MovieEntity(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath
        ))
    }
}