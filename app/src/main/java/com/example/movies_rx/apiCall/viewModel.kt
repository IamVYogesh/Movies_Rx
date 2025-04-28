package com.example.movies_rx.apiCall

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val api : ServiceApi) : ViewModel(){

    private var _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies : State<List<Movie>> = _movies

    private val disposable = CompositeDisposable()

    init {
        getMovies()
    }

    fun getMovies() {
        val subscription = api.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { moviesList ->
                    _movies.value = moviesList.data
                }
            )

        disposable.add(subscription)

    }

    override fun onCleared(){
        super.onCleared()
        disposable.clear()
    }
}