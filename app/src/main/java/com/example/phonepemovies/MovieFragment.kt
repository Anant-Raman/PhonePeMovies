package com.example.phonepemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.databinding.FragmentMovieBinding


private var _binding: FragmentMovieBinding? = null
private val binding get() = _binding!!
private const val MOVIE_RESULT = "param1"

class MovieFragment : Fragment() {

    private var movieResult: MovieResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        arguments?.let {
////            movieResult = Gson().fromJson(it.getString(MOVIE_RESULT),MovieResult::class.java)
//        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtRelease.text = movieResult?.release_date
        binding.txtOverview.text = movieResult?.overview
        binding.txtRating.text = movieResult?.vote_average.toString()
        binding.txtPopularity.text = movieResult?.popularity.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185" + movieResult?.poster_path)
            .into(binding.imgView)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MovieFragment().apply {
                arguments = Bundle().apply {
//                    putString(MOVIE_RESULT, Gson().toJson(result))
                }
            }
    }
}