package com.tes.assessment.persentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.tes.assessment.domain.usecase.movie.PopularMovieUseCase
import com.tes.assessment.domain.usecase.movie.UpcomingMovieUseCase
import com.tes.assessment.persentation.dashboard.popular.PopularState
import com.tes.assessment.persentation.dashboard.upcoming.UpcomingState
import com.tes.assessment.utils.Constants
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val upcomingMovieUseCase: UpcomingMovieUseCase
) : ViewModel() {
    private val _listPopularData = MutableStateFlow(PopularState())
    var listPopularData: StateFlow<PopularState> = _listPopularData

    private val _listUpcomingData = MutableStateFlow(UpcomingState())
    var listUpcomingData: StateFlow<UpcomingState> = _listUpcomingData

    fun getPopularMovie() = viewModelScope.launch(Dispatchers.IO) {
        popularMovieUseCase(Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listPopularData.value = PopularState(popularList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listPopularData.value = PopularState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listPopularData.value =
                        PopularState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }

    fun getUpcomingMovie() = viewModelScope.launch(Dispatchers.IO) {
        upcomingMovieUseCase(Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listUpcomingData.value = UpcomingState(upcomingList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listUpcomingData.value = UpcomingState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listUpcomingData.value =
                        UpcomingState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }
}