package com.tes.assessment.persentation.dashboard.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heycoding.assessment.databinding.ItemSliderMovieHomeBinding
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.utils.Constants

class BannerAdapter(
    var listMovieBannerData: ArrayList<Movies>
) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSliderMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Movies) {
            binding.apply {
                val poster: String =
                    Constants.BASE_URL_IMG_TMDB + "original" + banner.backdropPath

                Glide.with(itemView.context).load(poster).into(imgSliderHome)
            }
        }
    }

    fun setBannerData(banner: List<Movies>) {
        listMovieBannerData.clear()
        listMovieBannerData.addAll(banner)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSliderMovieHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovieBannerData[position])
    }

    override fun getItemCount(): Int = listMovieBannerData.size
}