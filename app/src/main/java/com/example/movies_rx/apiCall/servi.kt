package com.example.movies_rx.apiCall

import io.reactivex.Observable
import retrofit2.http.GET

interface ServiceApi {
    @GET("/movies/paginated")
    fun getMovies() : Observable<MovieResponse>
}