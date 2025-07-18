package com.example.composemovieapp.data

import com.example.composemovieapp.models.MovieModel
import com.example.moviescourseapp.data.local.FavoriteMovieEntity
import com.example.moviescourseapp.models.details.MovieDetailsModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): List<MovieModel>
    suspend fun getMovieDetails(movieId: String): MovieDetailsModel
    suspend fun insertMovie(movieEntity: FavoriteMovieEntity)
    suspend fun deleteMovie(movieEntity: FavoriteMovieEntity)
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
}