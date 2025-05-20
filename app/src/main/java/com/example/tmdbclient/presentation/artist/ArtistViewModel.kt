package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.GetArtistsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetArtistsUseCase
import com.example.tmdbclient.domain.UpdateArtistsUseCase

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase,
    private val getArtistsBasedOnNameUseCase: GetArtistsBasedOnNameUseCase
): ViewModel() {

    fun getArtists() = liveData {
        emit(getArtistsUseCase.execute())
    }

    fun getArtistsBasedOnName(name: String): LiveData<List<Artist>?>{
        getArtistsBasedOnNameUseCase.name = name
        return liveData {
            emit(getArtistsBasedOnNameUseCase.execute())
        }
    }

    fun updateArtists() = liveData {
        emit(updateArtistsUseCase.execute())
    }

}