package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.domain.GetArtistsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetArtistsUseCase
import com.example.tmdbclient.domain.GetMoviesBasedOnNameUseCase
import com.example.tmdbclient.domain.GetMoviesUseCase
import com.example.tmdbclient.domain.GetTvShowsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetTvShowsUseCase
import com.example.tmdbclient.domain.UpdateArtistsUseCase
import com.example.tmdbclient.domain.UpdateMoviesUseCase
import com.example.tmdbclient.domain.UpdateTvShowsUseCase
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateMoviesUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideMovieBasedOnNameUseCase(movieRepository: MovieRepository):GetMoviesBasedOnNameUseCase{
        return GetMoviesBasedOnNameUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository):GetArtistsUseCase{
        return GetArtistsUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun provideArtistsBasedOnNameUseCase(artistRepository: ArtistRepository): GetArtistsBasedOnNameUseCase{
        return GetArtistsBasedOnNameUseCase(artistRepository)
    }

    @Singleton
    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository):GetTvShowsUseCase{
        return GetTvShowsUseCase(tvShowRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase {
        return UpdateTvShowsUseCase(tvShowRepository)
    }

    @Singleton
    @Provides
    fun provideTvShowsBasedOnNameUseCase(tvShowRepository: TvShowRepository): GetTvShowsBasedOnNameUseCase{
        return GetTvShowsBasedOnNameUseCase(tvShowRepository)
    }
}