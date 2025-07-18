package com.example.composemovieapp.data

import com.example.composemovieapp.data.remote.MovieDbApi
import com.example.composemovieapp.models.MovieModel
import com.example.composemovieapp.models.transformToMovieDetailsModel
import com.example.composemovieapp.models.transformToMoviesModelList
import com.example.moviescourseapp.data.local.FavoriteMovieDao
import com.example.moviescourseapp.data.local.FavoriteMovieEntity
import com.example.moviescourseapp.models.details.MovieDetailsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi,
    private val movieDao: FavoriteMovieDao
) :
    MoviesRepository {

    override suspend fun getMovies(): List<MovieModel> {
        return movieDbApi.getMovies().transformToMoviesModelList()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsModel {
        return movieDbApi.getMovieDetails(movieId).transformToMovieDetailsModel()
    }

    override suspend fun insertMovie(movieEntity: FavoriteMovieEntity) {
         movieDao.insertMovie(movieEntity = movieEntity)
    }

    override suspend fun deleteMovie(movieEntity: FavoriteMovieEntity) {
         movieDao.deleteMovie(movieEntity = movieEntity)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

}

val mockMovieList = listOf(
    MovieModel(
        0,
        "Movie 1",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        1,
        "Movie 2",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        2,
        "Movie 3",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    ),
    MovieModel(
        3,
        "Movie 4",
        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        false
    )
)