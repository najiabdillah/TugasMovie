package com.tes.assessment.persentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import com.heycoding.assessment.databinding.FragmentDetailMovieBinding
import com.tes.assessment.domain.model.credit.Credits
import com.tes.assessment.domain.model.detail.DetailMovies
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.domain.model.review.Reviews
import com.tes.assessment.domain.model.trailer.Trailers
import com.tes.assessment.persentation.detail.credit.CreditAdapter
import com.tes.assessment.persentation.detail.review.ReviewAdapter
import com.tes.assessment.persentation.detail.trailer.TrailerAdapter
import com.tes.assessment.utils.Constants
import com.tes.assessment.utils.Helper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailMovieFragment : Fragment(), DetailMovieCallback {

    private var _fragmentDetailMovieFragment: FragmentDetailMovieBinding? = null
    private val fragmentDetailMovieFragment get() = _fragmentDetailMovieFragment
    private val detailMovieViewModel by viewModels<DetailMovieViewModel>()
    private lateinit var creditAdapter: CreditAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var trailerAdapter: TrailerAdapter
    private val listMovieTrailerData = ArrayList<Trailers>()
    private val listMovieCreditData = ArrayList<Credits>()
    private val listMovieReviewData = ArrayList<Reviews>()
    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDetailMovieFragment =
            FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        return fragmentDetailMovieFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditAdapter = CreditAdapter(ArrayList())
        reviewAdapter = ReviewAdapter(ArrayList())
        trailerAdapter = TrailerAdapter(ArrayList(), this)

        setupArguments()
        setupObserve()
    }

    private fun setupArguments() {
        val bundle = this.arguments
        if (bundle != null) {
            val dataTeams = bundle.getParcelable<Movies>("movie")
            movieId = dataTeams?.id.toString()
            detailMovieViewModel.apply {
                getDetailMovie(movieId)
                getCreditMovie(movieId)
                getReviewMovie(movieId)
                getTrailerMovie(movieId)
            }
        }
    }

    private fun setupObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailMovieViewModel.listDetailMovieData.collect { value ->
                        when {
                            value.isLoading -> {
                            }
                            value.error.isNotBlank() -> {
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.detailList.isNotEmpty() -> {
                                setupUIDetail(value.detailList)
                            }
                        }
                        delay(1000)
                    }
                }
                launch {
                    detailMovieViewModel.listCreditMovieData.collect { value ->
                        when {
                            value.isLoading -> {
                            }
                            value.error.isNotBlank() -> {
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.creditList.isNotEmpty() -> {
                                setupUICredit(value.creditList)
                            }
                        }
                        delay(1000)
                    }
                }
                launch {
                    detailMovieViewModel.listReviewMovieData.collect { value ->
                        when {
                            value.isLoading -> {
                            }
                            value.error.isNotBlank() -> {
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.reviewList.isNotEmpty() -> {
                                setupUIReview(value.reviewList)
                            }
                        }
                        delay(1000)
                    }
                }
                launch {
                    detailMovieViewModel.listTrailerMovieData.collect { value ->
                        when {
                            value.isLoading -> {
                            }
                            value.error.isNotBlank() -> {
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.trailerList.isNotEmpty() -> {
                                setupUITrailer(value.trailerList)
                            }
                        }
                        delay(1000)
                    }
                }
            }
        }
    }

    private fun setupUITrailer(trailerList: List<Trailers>) {
        listMovieTrailerData.clear()
        listMovieTrailerData.addAll(trailerList)
        trailerAdapter.setOnTrailerMovie(listMovieTrailerData)
    }

    private fun setupUIReview(reviewList: List<Reviews>) {
        listMovieReviewData.clear()
        listMovieReviewData.addAll(reviewList)
        reviewAdapter.setOnReviewMovie(listMovieReviewData)
    }

    private fun setupUICredit(creditList: List<Credits>) {
        listMovieCreditData.clear()
        listMovieCreditData.addAll(creditList)
        creditAdapter.setOnCreditMovie(listMovieCreditData)
    }

    private fun setupUIDetail(detailList: List<DetailMovies>) {
        fragmentDetailMovieFragment?.apply {
            detailList.forEach {
                val poster: String =
                    Constants.BASE_URL_IMG_TMDB + "w1280" + it.backdropPath
                Glide.with(requireContext()).load(poster).into(imgBanner)

                tvTitle.text = it.title
                tvTime.text = Helper.fromMinutesToHHmm(it.runtime)
                tvVote.text = it.voteAverage.toString()
                tvOverview.text = it.overview
                tvGenreOne.text = it.genres[0].name
                tvGenreTwo.text = it.genres[1].name
                tvGenreThree.text = it.genres[2].name
            }

            rvMovieCast.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = creditAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMovieCast)
            }

            rvMovieTrailer.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = trailerAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMovieTrailer)
            }

            rvMovieReview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = reviewAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMovieReview)
            }
        }
    }

    override fun onNavigateYoutube(trailers: Trailers) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("vnd.youtube:${trailers.key}")
            )
        )
    }
}