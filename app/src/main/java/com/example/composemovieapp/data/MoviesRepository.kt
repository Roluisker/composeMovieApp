package com.example.composemovieapp.data

import com.example.composemovieapp.models.MovieModel
import com.example.composemovieapp.models.transformToMoviesModelList

class MoviesRepository(private val movieDbApi: MovieDbApi) {

    suspend fun getMovies(): List<MovieModel> {
        return movieDbApi.getMovies().transformToMoviesModelList()
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