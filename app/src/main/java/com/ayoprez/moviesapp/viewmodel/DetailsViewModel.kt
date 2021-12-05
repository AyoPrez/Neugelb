package com.ayoprez.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayoprez.moviesapp.model.MovieModel
import com.ayoprez.moviesapp.viewmodel.repository.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {
    private val moviesLiveData = MutableLiveData<List<MovieModel>?>()

    fun getMovieDetails() = moviesLiveData

    init {

    }
}