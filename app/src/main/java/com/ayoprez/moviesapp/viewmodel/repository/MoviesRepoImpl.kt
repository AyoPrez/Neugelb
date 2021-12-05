package com.ayoprez.moviesapp.viewmodel.repository

import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val moviesApi: MoviesApi): MoviesRepo {

    override suspend fun getLatestMovies() = moviesApi.getLatestMovies()
    override suspend fun getMovieDetails(id: Int) = moviesApi.getMovieDetails(id)

}