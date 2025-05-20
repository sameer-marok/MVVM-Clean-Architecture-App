package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.GetArtistsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetArtistsUseCase
import com.example.tmdbclient.domain.UpdateArtistsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.http.GET
import javax.inject.Singleton

// To provide ArtistViewModelFactory
@Module
class ArtistModule {

    @ArtistScope // We want ViewModel to persist only till lifeCycle of activity
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase,
        getArtistsBasedOnNameUseCase: GetArtistsBasedOnNameUseCase
    ):ArtistViewModelFactory{
        return ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase, getArtistsBasedOnNameUseCase)
    }
}