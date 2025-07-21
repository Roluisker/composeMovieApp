package com.example.composemovieapp.presentation.favorites

import com.example.composemovieapp.models.MovieModel
import com.example.composemovieapp.presentation.home.ErrorMessage

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val movieList : List<MovieModel> = emptyList(),
    val errorMessage: ErrorMessage? = null
)