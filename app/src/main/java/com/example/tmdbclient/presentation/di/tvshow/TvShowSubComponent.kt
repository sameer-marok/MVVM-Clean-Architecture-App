package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.tvshow.TvShowActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(tvShowActivity: TvShowActivity)

    // Factory to provide TvShowSubComponent
    @Subcomponent.Factory
    interface Factory{
        fun create(): TvShowSubComponent
    }
}