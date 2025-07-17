package com.example.composemovieapp.data.remote

import com.example.composemovieapp.data.remote.detail.MovieDetailsResponse
import com.example.composemovieapp.data.MoviesResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
    ): MoviesResultResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "es-MX",
        @Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
    ): MovieDetailsResponse
}