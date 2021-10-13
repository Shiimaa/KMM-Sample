package com.kmmsampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kmmsampleapp.databinding.MovieItemLayoutBinding
import com.shared.data.MovieItem

class PopularMoviesRecyclerAdapter :
    RecyclerView.Adapter<PopularMoviesRecyclerAdapter.PopularMoviesHolder>() {
    private var movieItemList: List<MovieItem>? = null

    fun setMovieItemList(movieItemList: List<MovieItem>) {
        this.movieItemList = movieItemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesHolder {
        return PopularMoviesHolder(
            DataBindingUtil.inflate<MovieItemLayoutBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movie_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesHolder, position: Int) {
        holder.binding.movieItemMovieTitle.text = movieItemList!![position].title
        holder.binding.movieItemMovieVote.text =
            movieItemList!![position].vote_average.toString()
    }

    override fun getItemCount(): Int {
        return if (movieItemList == null)
            0
        else
            movieItemList!!.size
    }

    class PopularMoviesHolder(val binding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}