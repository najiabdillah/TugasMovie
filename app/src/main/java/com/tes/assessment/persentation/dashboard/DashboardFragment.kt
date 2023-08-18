package com.tes.assessment.persentation.dashboard

import android.os.Bundle
import android.os.Handler
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import com.heycoding.assessment.R
import com.heycoding.assessment.databinding.FragmentDashboardBinding
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.persentation.dashboard.banner.BannerAdapter
import com.tes.assessment.persentation.dashboard.popular.PopularAdapter
import com.tes.assessment.persentation.dashboard.upcoming.UpcomingAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment(), DashboardCallback {

    private var _fragmentDashboardBinding: FragmentDashboardBinding? = null
    private val fragmentDashboardBinding get() = _fragmentDashboardBinding
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private val listMoviePopularData = ArrayList<Movies>()
    private val listMovieBannerData = ArrayList<Movies>()
    private val listUpcomingMovieData = arrayListOf<Movies>()
    private var sliderhandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDashboardBinding =
            FragmentDashboardBinding.inflate(layoutInflater, container, false)

        dashboardViewModel.apply {
            getPopularMovie()
            getUpcomingMovie()
        }

        return fragmentDashboardBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerAdapter = BannerAdapter(ArrayList())
        popularAdapter = PopularAdapter(ArrayList(), this)
        upcomingAdapter = UpcomingAdapter(ArrayList(), this)

        setupObserve()
        setupUI()
    }

    private fun setupObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    dashboardViewModel.listUpcomingData.collect { value ->
                        when {
                            value.isLoading -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.VISIBLE
                            }
                            value.error.isNotBlank() -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.upcomingList.isNotEmpty() -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.GONE
                                listUpcomingMovieData.clear()
                                listUpcomingMovieData.addAll(value.upcomingList)
                                upcomingAdapter.setOnUpcomingMovie(listUpcomingMovieData)

                                listMovieBannerData.clear()
                                listMovieBannerData.addAll(value.upcomingList)
                                bannerAdapter.setBannerData(listUpcomingMovieData)
                            }
                        }
                        delay(1000)
                    }
                }
                launch {
                    dashboardViewModel.listPopularData.collect { value ->
                        when {
                            value.isLoading -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.VISIBLE
                            }
                            value.error.isNotBlank() -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(requireContext(), value.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                            value.popularList.isNotEmpty() -> {
                                fragmentDashboardBinding?.progressBar?.visibility = View.GONE
                                listMoviePopularData.clear()
                                listMoviePopularData.addAll(value.popularList)
                                popularAdapter.setOnPopularMovie(listMoviePopularData)
                            }
                        }
                        delay(1000)
                    }
                }
            }
        }
    }

    private fun setupUI() {
        fragmentDashboardBinding?.apply {
            vpBannerDashboard.apply {
                adapter = bannerAdapter

                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false

                val compositePageTransformer = CompositePageTransformer()
                compositePageTransformer.addTransformer(MarginPageTransformer(30))
                compositePageTransformer.addTransformer { page, position ->
                    val r = 1 - kotlin.math.abs(position)
                    page.scaleY = 0.85f + r * 0.25f
                }

                setPageTransformer(compositePageTransformer)

                vpBannerDashboard.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        sliderhandler.removeCallbacks(sliderRunnable)
                        sliderhandler.postDelayed(sliderRunnable, 5000)
                    }
                })
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }

            rvMoviePopular.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = popularAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMoviePopular)
            }

            rvMovieUpcoming.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingAdapter
                clipToPadding = false
                clipChildren = false
                val snapHelper: SnapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(rvMovieUpcoming)
            }
        }
    }

    private val sliderRunnable = Runnable {
        fragmentDashboardBinding?.vpBannerDashboard?.currentItem =
            fragmentDashboardBinding?.vpBannerDashboard?.currentItem?.plus(1)!!
    }

    override fun onDetailMovie(movie: Movies) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(
            R.id.action_navigation_dashboard_to_detailMovieFragment,
            bundle
        )
    }

    override fun onResume() {
        super.onResume()
        sliderhandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        sliderhandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDashboardBinding = null
    }
}