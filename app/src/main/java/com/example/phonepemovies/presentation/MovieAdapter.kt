package com.example.phonepemovies.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.phonepemovies.data.model.MovieResult
import com.example.phonepemovies.databinding.MovieItemBinding

class MovieAdapter(
    val onMovieClicked: (movie: MovieResult, position: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private val movieList: MutableList<MovieResult?> = mutableListOf()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieItemBinding.inflate(layoutInflater, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieAdapter.ViewHolder).bind(movieList[position])

    }

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(private val viewBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(movie: MovieResult?) {
            viewBinding.tvMovieName.text = movie?.title
            viewBinding.tvMovieDescription.text = movie?.overview
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185"+movie?.poster_path)
                .into(viewBinding.ivCategoryIcon)

            viewBinding.rootView.setOnClickListener {
                movie?.let { it1 -> onMovieClicked(it1, layoutPosition) }
            }
        }
    }

    fun addList(list: List<MovieResult?>) {
        if (list.isNotEmpty()) {
            val newIndex = movieList.size
            val newItemsCount = list.size
            if (movieList.addAll(list)) notifyItemRangeInserted(newIndex, newItemsCount)
        }
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}