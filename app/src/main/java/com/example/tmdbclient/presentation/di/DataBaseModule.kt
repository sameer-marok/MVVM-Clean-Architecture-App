package com.example.tmdbclient.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// this module is to provide dependencies related to Database
@Module
class DataBaseModule {

    // Provides TMDBDatabase instance
    @Singleton
    @Provides
    fun provideMovieDB(context: Context):TMDBDatabase{
        return Room.databaseBuilder(context, TMDBDatabase::class.java, name = "tmdbClient")
            .build()
    }

    // Need to create function to provide DAOs since used in TMDBDatabase
    // provides MovieDao instance
    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase):MovieDao{
        return tmdbDatabase.movieDao()
    }

    // provides ArtistDao instance
    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase):ArtistDao{
        return tmdbDatabase.artistDao()
    }

    // provides MovieDao instance
    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase):TvShowDao{
        return tmdbDatabase.tvDao()
    }
}