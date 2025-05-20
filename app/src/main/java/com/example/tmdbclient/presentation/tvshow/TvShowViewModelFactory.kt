package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.domain.GetTvShowsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetTvShowsUseCase
import com.example.tmdbclient.domain.UpdateTvShowsUseCase

class TvShowViewModelFactory(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase,
    private val getTvShowsBasedOnNameUseCase: GetTvShowsBasedOnNameUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShowsUseCase, updateTvShowsUseCase, getTvShowsBasedOnNameUseCase) as T
    }
}