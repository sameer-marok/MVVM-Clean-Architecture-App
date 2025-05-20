package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(movieActivity: MovieActivity)

    // Factory to provide MovieSubComponent
    @Subcomponent.Factory
    interface Factory{
        fun create(): MovieSubComponent
    }
}