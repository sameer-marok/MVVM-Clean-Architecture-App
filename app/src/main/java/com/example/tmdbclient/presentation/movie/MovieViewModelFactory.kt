package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.tmdbclient.domain.GetMoviesBasedOnNameUseCase
import com.example.tmdbclient.domain.GetMoviesUseCase
import com.example.tmdbclient.domain.UpdateMoviesUseCase

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase,
    private val getMoviesBasedOnNameUseCase: GetMoviesBasedOnNameUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase, getMoviesBasedOnNameUseCase) as T
    }
}