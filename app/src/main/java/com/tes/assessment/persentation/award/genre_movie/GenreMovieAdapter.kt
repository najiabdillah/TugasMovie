package com.tes.assessment.persentation.award.genre_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heycoding.assessment.databinding.ItemMovieAwardBinding
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.persentation.award.AwardCallback
import com.tes.assessment.utils.Constants


class GenreMovieAdapter(
    var listGenreMovie: ArrayList<Movies>,
    private val callback: AwardCallback
) :
    RecyclerView.Adapter<GenreMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieAwardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            binding.apply {
                val poster: String =
                    Constants.BASE_URL_IMG_TMDB + "original" + movie.posterPath
                Glide.with(itemView.context).load(poster).into(imgPosterAward)
                tvTitle.text = movie.title
                tvDesc.text = movie.overview

                itemView.setOnClickListener {
                    callback.onDetailAwardMovie(movie)
                }
            }
        }
    }

    fun setOnGenreMovie(movie: List<Movies>) {
        listGenreMovie.clear()
        listGenreMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieAwardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGenreMovie[position])
    }

    override fun getItemCount(): Int = listGenreMovie.size

    fun filterList(filterList: ArrayList<Movies>) {
        listGenreMovie = filterList
        notifyDataSetChanged()
    }
}