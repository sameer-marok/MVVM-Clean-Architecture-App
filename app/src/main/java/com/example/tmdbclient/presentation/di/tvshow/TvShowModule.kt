package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.GetTvShowsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetTvShowsUseCase
import com.example.tmdbclient.domain.UpdateTvShowsUseCase
import com.example.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

// To provide TvShowViewModelFactory
@Module
class TvShowModule {

    @TvShowScope // We want ViewModel to persist only till lifeCycle of activity
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase,
        getTvShowsBasedOnNameUseCase: GetTvShowsBasedOnNameUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase, getTvShowsBasedOnNameUseCase)
    }
}