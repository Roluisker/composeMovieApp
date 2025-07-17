package com.example.composemovieapp.presentation.detail

import com.example.composemovieapp.presentation.home.ErrorMessage
import com.example.moviescourseapp.models.details.MovieDetailsModel

data class MovieDetailsUiState(
  val movieDetailsModel: MovieDetailsModel? = null,
  val isLoading: Boolean = false,
  val error: ErrorMessage? = null,
)
