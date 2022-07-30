package com.example.phonepemovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(onMovieClicked = onMovieClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = getViewModel()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        setUpObserver()
        initRecyclerView()
    }


    private fun setUpObserver() {
        viewModel.movieResult.observe(viewLifecycleOwner) {
            viewModel.list = it as List<MovieResult>
            movieAdapter.addList(it)
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)
        _binding?.rvVideoList?.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }
    }

    private val onMovieClick = fun(movie: MovieResult, position: Int) {
        Toast.makeText(context, "Under Progress", Toast.LENGTH_SHORT).show()
    }


}