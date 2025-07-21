package com.example.composemovieapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.data.MoviesRepository
import com.example.composemovieapp.models.MovieModel
import com.example.composemovieapp.models.transformToMovieEntity
import com.example.composemovieapp.models.transformToMovieModel
import com.example.composemovieapp.presentation.home.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _favoriteUiState = MutableStateFlow(FavoriteMoviesUiState())
    val favoriteMoviesUiState = _favoriteUiState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
          try {
              _favoriteUiState.update {
                  it.copy(isLoading = true)
              }
              moviesRepository.getFavoriteMovies().collect { favoriteMovieList ->
                  _favoriteUiState.update {
                      it.copy(
                          isLoading = false,
                          movieList = favoriteMovieList.map { movieEntity ->
                              movieEntity.transformToMovieModel()
                          }
                      )
                  }
              }
          } catch (e: Exception) {
              _favoriteUiState.update {
                  it.copy(
                      isLoading = false,
                      errorMessage = ErrorMessage.DATABASE_ERROR
                  )
              }
          }
        }
    }

    fun deleteMovieFromFavorites(movieModel: MovieModel) {
        viewModelScope.launch {
            moviesRepository.deleteMovie(movieEntity = movieModel.transformToMovieEntity())
        }
    }
}
