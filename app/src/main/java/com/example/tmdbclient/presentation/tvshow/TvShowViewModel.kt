package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.GetTvShowsBasedOnNameUseCase
import com.example.tmdbclient.domain.GetTvShowsUseCase
import com.example.tmdbclient.domain.UpdateTvShowsUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase,
    private val getTvShowsBasedOnNameUseCase: GetTvShowsBasedOnNameUseCase
):ViewModel() {

    fun getTvShows() = liveData {
        emit(getTvShowsUseCase.execute())
    }

    fun getTvShowsBasedOnName(name:String): LiveData<List<TvShow>?>{
        getTvShowsBasedOnNameUseCase.name = name
        return liveData {
            val tvShowList = getTvShowsBasedOnNameUseCase.execute()
            emit(tvShowList)
        }
    }

    fun updateTvShows() = liveData {
        emit(updateTvShowsUseCase.execute())
    }

}