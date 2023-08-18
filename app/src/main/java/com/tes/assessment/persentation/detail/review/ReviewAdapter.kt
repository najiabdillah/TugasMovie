package com.tes.assessment.persentation.detail.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heycoding.assessment.databinding.ItemMovieReviewBinding
import com.tes.assessment.domain.model.review.Reviews
import com.tes.assessment.utils.Helper

class ReviewAdapter(var listReviewMovie: ArrayList<Reviews>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Reviews) {
            binding.apply {
                tvTitleNewsHome.text = movie.content
                tvDateNewsHome.text = Helper.timeAgo(movie.createdAt)
            }
        }
    }

    fun setOnReviewMovie(movie: List<Reviews>) {
        listReviewMovie.clear()
        listReviewMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listReviewMovie[position])
    }

    override fun getItemCount(): Int = listReviewMovie.size
}