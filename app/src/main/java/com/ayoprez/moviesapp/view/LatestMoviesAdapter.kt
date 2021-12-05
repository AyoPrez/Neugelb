package com.ayoprez.moviesapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ayoprez.moviesapp.R
import com.ayoprez.moviesapp.model.MovieModel

class LatestMoviesAdapter constructor(
    private val movieList: List<MovieModel>?
) : RecyclerView.Adapter<LatestMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_movies, parent, false)

        return LatestMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LatestMoviesViewHolder, position: Int) {
        holder.image.load(movieList?.get(position)?.poster_path ?: "")
        holder.title.text = movieList?.get(position)?.title ?: ""
        holder.itemView.setOnClickListener {
            val movieId: Int = movieList?.get(position)?.id ?: -1
            val action = DetailsFragmentDirections.actionDetailsFragmentToMainFragment(movieId)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }
}

class LatestMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: AppCompatImageView = itemView.findViewById(R.id.movie_pic)
    val title: AppCompatTextView = itemView.findViewById(R.id.movie_title)
}