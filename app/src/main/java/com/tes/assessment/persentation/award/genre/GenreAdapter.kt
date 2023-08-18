package com.tes.assessment.persentation.award.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heycoding.assessment.R
import com.heycoding.assessment.databinding.ItemMovieGenreBinding
import com.tes.assessment.domain.model.genre.Genres
import com.tes.assessment.persentation.award.AwardCallback

class GenreAdapter(
    var listGenreMovie: ArrayList<Genres>,
    private val callback: AwardCallback
) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class ViewHolder(private val binding: ItemMovieGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Genres) {
            binding.apply {
                tvGenre.text = movie.name

                itemView.setOnClickListener {
                    callback.onGenreMovieId(movie)
                    selectedItemPos = adapterPosition
                    if (lastItemSelectedPos == -1)
                        lastItemSelectedPos = selectedItemPos
                    else {
                        notifyItemChanged(lastItemSelectedPos)
                        lastItemSelectedPos = selectedItemPos
                    }
                    notifyItemChanged(selectedItemPos)
                }
            }
        }

        fun defaultBg() {
            binding.llGenre.setBackgroundResource(R.drawable.bg_outline_black)
        }

        fun selectedBg() {
            binding.llGenre.setBackgroundResource(R.drawable.bg_outline_yellow)
        }
    }

    fun setOnGenreMovie(movie: List<Genres>) {
        listGenreMovie.clear()
        listGenreMovie.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = listGenreMovie[position]
        holder.bind(genre)
        if (position == selectedItemPos)
            holder.selectedBg()
        else
            holder.defaultBg()
    }

    override fun getItemCount(): Int = listGenreMovie.size
}