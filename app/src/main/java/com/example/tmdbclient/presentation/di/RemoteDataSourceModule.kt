package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.repository.artist.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.TvShowRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// this module provides dependencies related to Remote data sources
@Module
class RemoteDataSourceModule(private val apiKey: String) {

    // -----------------------------------Remote data source
    // Movie
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService):MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }

    // Artist
    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService):ArtistRemoteDataSource{
        return ArtistRemoteDataSourceImpl(tmdbService, apiKey)
    }

    // TvShow
    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService):TvShowRemoteDataSource{
        return TvShowRemoteDataSourceImpl(tmdbService, apiKey)
    }

}