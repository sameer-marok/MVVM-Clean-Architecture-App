package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.repository.artist.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.ArtistCacheDataSourceImpl
import com.example.tmdbclient.data.repository.movie.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.MovieCacheDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// this module provides dependencies related to Cache data source
@Module
class CacheDataSourceModule {

    //Movie
    @Singleton
    @Provides
    fun provideMovieCacheDataSource():MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }

    //Artist
    @Singleton
    @Provides
    fun provideArtistCacheDataSource():ArtistCacheDataSource{
        return ArtistCacheDataSourceImpl()
    }

    //TvShow
    @Singleton
    @Provides
    fun provideTvShowCacheDataSource():TvShowCacheDataSource{
        return TvShowCacheDataSourceImpl()
    }
}