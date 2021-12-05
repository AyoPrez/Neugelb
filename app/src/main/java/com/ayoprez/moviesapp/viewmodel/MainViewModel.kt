package com.ayoprez.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayoprez.moviesapp.model.MovieModel
import com.ayoprez.moviesapp.viewmodel.repository.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {
    private val moviesLiveData = MutableLiveData<List<MovieModel>>()
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun getLatestMovies() = moviesLiveData
    fun getLoadingState() = loading
    fun getErrorMessage() = errorMessage

    init {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepo.getLatestMovies()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    moviesLiveData.postValue(response.body())
                } else {
                    errorMessage.value = onError(response.errorBody())
                }
                loading.value = false
            }
        }

        //Dummy data
//        val readStringFromJsonFile =
//            Utils().readStringFromJsonFile(getApplication(), "movie_data.json")


//        val movieModels: List<MovieModel> = GsonBuilder().create().fromJson(readStringFromJsonFile, Array<MovieModel>::class.java).toList()
//        moviesLiveData.value = movieModels
    }

    private fun onError(error: ResponseBody?): String {
        //TODO Process this error
        return error?.string() ?: "Unknown error loading latest movies"
    }
}