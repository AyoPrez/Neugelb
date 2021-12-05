package com.ayoprez.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayoprez.moviesapp.model.MovieDetails
import com.ayoprez.moviesapp.viewmodel.repository.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {
    private val moviesDetailsData = MutableLiveData<MovieDetails>()
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun getMovieDetails(movieId: Int) {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepo.getMovieDetails(movieId)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    moviesDetailsData.postValue(response.body())
                } else {
                    errorMessage.value = onError(response.errorBody())
                }
                loading.value = false
            }
        }
    }

    private fun onError(error: ResponseBody?): String {
        //TODO Process this error
        return error?.string() ?: "Unknown error loading latest movies"
    }
}