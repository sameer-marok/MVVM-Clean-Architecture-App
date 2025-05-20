package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.data.repository.artist.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.movie.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule() {

    // MovieRepository
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ):MovieRepository{
        return MovieRepositoryImpl(
            movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource
        )
    }

    //ArtistRepository
    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistCacheDataSource: ArtistCacheDataSource,
        artistLocalDataSource: ArtistLocalDataSource
    ):ArtistRepository{
        return ArtistRepositoryImpl(
            artistRemoteDataSource, artistCacheDataSource, artistLocalDataSource
        )
    }

    //TvSHowRepository
    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource
    ):TvShowRepository{
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,tvShowCacheDataSource, tvShowLocalDataSource
        )
    }
}