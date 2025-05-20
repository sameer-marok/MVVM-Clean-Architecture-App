package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.repository.artist.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.ArtistLocalDataSourceImpl
import com.example.tmdbclient.data.repository.movie.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.MovieLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// this module provides dependencies related to Local data sources
@Module
class LocalDataSourceModule {

    //Movie
    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao):MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    //Artist
    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao):ArtistLocalDataSource{
        return ArtistLocalDataSourceImpl(artistDao)
    }

    //TvShow
    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao):TvShowLocalDataSource{
        return TvShowLocalDataSourceImpl(tvShowDao)
    }
}