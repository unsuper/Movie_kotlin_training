package com.training.movieinfo.api

import com.training.movieinfo.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderAPI {
    @GET("/v3/movie/get/all")
    fun getAllMovieAsync() : Deferred<Response<Movie>>

    @GET("/v3/movie/get/id/{id}")
    fun getMovieByIdAsync(@Path("id") movieId: String) : Deferred<Response<Movie.MovieItem>>
}