package com.example.phonepemovies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.data.repository.MovieRepository
import com.example.phonepemovies.domain.AppResult
import com.example.videoplayer.common.helper.NetworkHelper
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    var list: List<MovieResult> = emptyList()

    private val _movieResult = MutableLiveData<ArrayList<MovieResult>?>()
    val movieResult: LiveData<ArrayList<MovieResult>?>
        get() = _movieResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    init {
        fetchVideos()
    }
    private fun fetchVideos() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected())
                when (val result = movieRepository.getPopularMovies()) {
                    is AppResult.Success -> {
                        _movieResult.postValue(result.data)
                        _errorMessage.postValue("")
                    }
                    is AppResult.Failure -> {
                        _errorMessage.postValue("Something went wrong!!")
                    }
                }
        }
    }

}