package com.tes.assessment.persentation.dashboard.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heycoding.assessment.databinding.ItemMovieDashboardBinding
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.persentation.dashboard.DashboardCallback
import com.tes.assessment.utils.Constants

class PopularAdapter(
    var listPopularMovie: ArrayList<Movies>,
    private val callback: DashboardCallback
) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            binding.apply {
                val poster: String =
                    Constants.BASE_URL_IMG_TMDB + "w500" + movie.posterPath
                Glide.with(itemView.context).load(poster).into(imgPoster)

                itemView.setOnClickListener {
                    callback.onDetailMovie(movie)
                }
            }
        }
    }

    fun setOnPopularMovie(movie: List<Movies>) {
        listPopularMovie.clear()
        listPopularMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieDashboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPopularMovie[position])
    }

    override fun getItemCount(): Int = listPopularMovie.size
}