package com.example.phonepemovies.data.api

import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.domain.AppError
import com.example.phonepemovies.domain.AppResult
import retrofit2.awaitResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getPopularMovies(): AppResult<ArrayList<MovieResult>> {

        val result =
            apiService.getPopularMovieList(
                apiKey = "38a73d59546aa378980a88b645f487fc",
                language = "en-US",
                page = "1"
            )
                .awaitResponse()
        return if (result.isSuccessful) {
            val arr = arrayListOf<MovieResult>()
            result.body()?.results?.forEach {
                arr.add(it)
            }
            AppResult.Success(arr)
        } else {
            AppResult.Failure(AppError("001", "Error found!!"))
        }
    }
}
