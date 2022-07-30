package com.example.phonepemovies.data.api

import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.domain.AppResult

interface ApiHelper {
    suspend fun getPopularMovies(): AppResult<ArrayList<MovieResult>>
}