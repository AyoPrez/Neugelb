package com.ayoprez.moviesapp.viewmodel.repository

import com.ayoprez.moviesapp.model.MovieDetails
import com.ayoprez.moviesapp.model.MovieModel
import retrofit2.Response

interface MoviesRepo {
    suspend fun getLatestMovies() : Response<MovieModel>
    suspend fun getMovieDetails(id: String) : Response<MovieDetails>
}