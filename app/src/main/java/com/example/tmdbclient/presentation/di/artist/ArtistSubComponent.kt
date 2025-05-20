package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.presentation.artist.ArtistActivity
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class]) // list of modules related to this subcomponent
interface ArtistSubComponent {

    // we'll use this function to inject dep to artist activity
    fun inject(artistActivity: ArtistActivity)

    // we define subcomponent factory here so that AppComponent knows how to create instances
    // of this ArtistSubComponent
    @Subcomponent.Factory
    interface Factory{
        fun create():ArtistSubComponent
    }
}