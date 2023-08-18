package com.tes.assessment.persentation.detail.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heycoding.assessment.databinding.ItemMovieTrailerBinding
import com.tes.assessment.domain.model.trailer.Trailers
import com.tes.assessment.persentation.detail.DetailMovieCallback

class TrailerAdapter(
    var listTrailerMovie: ArrayList<Trailers>,
    private val callback: DetailMovieCallback
) :
    RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Trailers) {
            binding.apply {
                tvTitleTrailer.text = movie.name
                cvYoutubeTrailer.setOnClickListener {
                    callback.onNavigateYoutube(movie)
                }
            }
        }
    }

    fun setOnTrailerMovie(movie: List<Trailers>) {
        listTrailerMovie.clear()
        listTrailerMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieTrailerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTrailerMovie[position])
    }

    override fun getItemCount(): Int = listTrailerMovie.size
}