package com.example.composemovieapp.di

import android.app.Application
import androidx.room.Room
import com.example.composemovieapp.data.MoviesRepository
import com.example.composemovieapp.data.remote.MovieDbApi
import com.example.composemovieapp.data.MoviesRepositoryImpl
import com.example.moviescourseapp.data.local.FavoriteMovieDao
import com.example.moviescourseapp.data.local.FavoriteMovieDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Singleton
    @Provides
    fun providesDatabaseClass(application: Application): FavoriteMovieDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = FavoriteMovieDatabase::class.java,
            name = "favorite_movie_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesFavoriteMovieDao(favoriteMovieDatabase: FavoriteMovieDatabase): FavoriteMovieDao {
        return favoriteMovieDatabase.createFavoriteMovieDao()
    }

    @Singleton
    @Provides
    fun retrofitProvider(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun movieDbApiProvider(retrofit: Retrofit) : MovieDbApi {
        return retrofit.create(MovieDbApi::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binds {

    @Binds
    abstract fun moviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

}