package com.example.phonepemovies.data.repository

import com.example.phonepemovies.data.api.ApiHelper
import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.domain.AppResult

class MovieRepository(private val apiHelper: ApiHelper) {
    suspend fun getPopularMovies(): AppResult<ArrayList<MovieResult>> = apiHelper.getPopularMovies()
}