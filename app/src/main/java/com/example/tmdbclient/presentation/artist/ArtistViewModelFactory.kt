package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.domain.GetArtistsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetArtistsUseCase
import com.example.tmdbclient.domain.UpdateArtistsUseCase

class ArtistViewModelFactory(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase,
    private val getArtistsBasedOnNameUseCase: GetArtistsBasedOnNameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtistViewModel(getArtistsUseCase, updateArtistsUseCase, getArtistsBasedOnNameUseCase) as T
    }

}