package com.ayoprez.moviesapp.viewmodel.repository

class MoviesRepoImpl(private val moviesApi: MoviesApi): MoviesRepo {

    override suspend fun getLatestMovies() = moviesApi.getLatestMovies()
    override suspend fun getMovieDetails(id: String) = moviesApi.getMovieDetails(id)

}