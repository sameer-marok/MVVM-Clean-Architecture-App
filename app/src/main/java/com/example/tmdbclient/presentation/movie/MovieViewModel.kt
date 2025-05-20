package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.GetMoviesBasedOnNameUseCase
import com.example.tmdbclient.domain.GetMoviesUseCase
import com.example.tmdbclient.domain.UpdateMoviesUseCase
import kotlinx.coroutines.Dispatchers

class MovieViewModel (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase,
    private val getMoviesBasedOnNameUseCase: GetMoviesBasedOnNameUseCase
): ViewModel() {

    // this function will execute use case and emit list as liveData
    // Using coroutine's liveData block, we didn't use dispatcher here
    // so coroutine will use main thread
    fun getMovies() : LiveData<List<Movie>?>{
       return liveData {
            val movieList = getMoviesUseCase.execute()
           emit(movieList)
        }
    }

    fun getMoviesBasedOnName(name:String): LiveData<List<Movie>?>{
        getMoviesBasedOnNameUseCase.name = name
        return liveData {
            val movieList = getMoviesBasedOnNameUseCase.execute()
            emit(movieList)
        }
    }

    fun updateMovieList() = liveData {
        val movieList = updateMoviesUseCase.execute()
        emit(movieList)
    }

    override fun onCleared() {
        super.onCleared()
        getMoviesBasedOnNameUseCase
    }

}