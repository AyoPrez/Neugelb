package com.ayoprez.moviesapp.viewmodel.repository

import com.ayoprez.moviesapp.model.MovieDetails
import com.ayoprez.moviesapp.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/latest")
    suspend fun getLatestMovies(): Response<List<MovieModel>>

    @GET("movie/")
    suspend fun getMovieDetails(@Query("movie_id")id: Int): Response<MovieDetails>
}