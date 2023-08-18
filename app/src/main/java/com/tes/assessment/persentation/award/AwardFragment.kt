package com.tes.assessment.persentation.award

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import dagger.hilt.android.AndroidEntryPoint
import com.heycoding.assessment.R
import com.heycoding.assessment.databinding.FragmentAwardBinding
import com.tes.assessment.domain.model.genre.Genres
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.persentation.award.genre.GenreAdapter
import com.tes.assessment.persentation.award.genre_movie.GenreMovieAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AwardFragment : Fragment(), AwardCallback {

    private var _fragmentAwardBinding: FragmentAwardBinding? = null
    private val fragmentAwardBinding get() = _fragmentAwardBinding
    private val awardViewModel by viewModels<AwardViewModel>()
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var genreMovieAdapter: GenreMovieAdapter
    private val listMovieGenreData = ArrayList<Genres>()
    private val listSearchMovieData = arrayListOf<Movies>()
    private var page = 1
    private var totalPage: Int = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _fragmentAwardBinding = FragmentAwardBinding.inflate(layoutInflater, container, false)

        awardViewModel.apply {
            getGenre()
            getGenreMovieFetching()
        }

        return fragmentAwardBinding?.root
    }

    override fun onGenreMovieId(movieId: Genres) {
        awardViewModel.getGenreMovie(movieId.id.toString(), page.toString())
    }

    private fun getGenreMovieFetching() {
        awardViewModel.getGenreMovie("", page.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genreAdapter = GenreAdapter(ArrayList(), this)

        setupObserve()
        setupUI()
    }

    private fun setupObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    awardViewModel.listGenreData.collect { value ->
                        when {
                            value.isLoading -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.VISIBLE
                            }
                            value.error.isNotBlank() -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.genreList.isNotEmpty() -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.GONE
                                listMovieGenreData.clear()
                                listMovieGenreData.addAll(value.genreList)
                                genreAdapter.setOnGenreMovie(listMovieGenreData)
                            }
                        }
                        delay(1000)
                    }
                }
                launch {
                    awardViewModel.listGenreMovieData.collect { value ->
                        when {
                            value.isLoading -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.VISIBLE
                            }
                            value.error.isNotBlank() -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.genreMovieList.isNotEmpty() -> {
                                fragmentAwardBinding?.progressBar?.visibility = View.GONE
                                listSearchMovieData.clear()
                                listSearchMovieData.addAll(value.genreMovieList)
                                genreMovieAdapter.notifyDataSetChanged()
                                genreMovieAdapter.setOnGenreMovie(listSearchMovieData)
                            }
                        }
                        delay(1000)
                    }
                }
            }
        }
    }

    private fun setupUI() {
        genreMovieAdapter = GenreMovieAdapter(ArrayList(), this)

        fragmentAwardBinding?.apply {
            rvMovieGenre.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = genreAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMovieGenre)
            }
            rvMovieAward.apply {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = genreMovieAdapter
                clipToPadding = false
                clipChildren = false
            }
            edtSearch.apply {
                addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable) {

                    }

                    override fun beforeTextChanged(
                        s: CharSequence, start: Int,
                        count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence, start: Int,
                        before: Int, count: Int
                    ) {
                        filterMovie(s)

                        if (edtSearch.text.toString().isNotEmpty()) {
                            fragmentAwardBinding?.tvMovieFilter?.text = "Cari ‘${s}’"
                            fragmentAwardBinding?.tvMovieFilter?.visibility = View.VISIBLE
                        }
                    }
                })
            }

        }
    }

    private fun filterMovie(s: CharSequence) {
        val filteredList: ArrayList<Movies> = ArrayList()
        for (item in listSearchMovieData) {
            if (item.title.lowercase().contains(s.toString())) filteredList.add(item)
        }

        if (filteredList.isEmpty()) getGenreMovieFetching()
        else genreMovieAdapter.filterList(
            filteredList
        )
    }

    override fun onDetailAwardMovie(movie: Movies) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.action_navigation_award_to_detailMovieFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentAwardBinding = null
    }
}