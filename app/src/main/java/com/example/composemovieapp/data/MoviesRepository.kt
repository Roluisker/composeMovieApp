package com.example.composemovieapp.data

import com.example.composemovieapp.models.MovieModel

interface MoviesRepository {
    suspend fun getMovies(): List<MovieModel>
}