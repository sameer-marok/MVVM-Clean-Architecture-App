package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.domain.GetMoviesBasedOnNameUseCase
import com.example.tmdbclient.domain.GetMoviesUseCase
import com.example.tmdbclient.domain.UpdateMoviesUseCase
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

// To provide MovieViewModelFactory
@Module
class MovieModule {

    @MovieScope // We want ViewModel to persist only till lifeCycle of activity
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase,
        getMoviesBasedOnNameUseCase: GetMoviesBasedOnNameUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase, getMoviesBasedOnNameUseCase)
    }
}