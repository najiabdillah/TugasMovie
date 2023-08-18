package com.tes.assessment.persentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.tes.assessment.domain.usecase.credit.CreditUseCase
import com.tes.assessment.domain.usecase.detail.DetailMovieUseCase
import com.tes.assessment.domain.usecase.review.ReviewUseCase
import com.tes.assessment.domain.usecase.trailer.TrailerUseCase
import com.tes.assessment.persentation.detail.credit.CreditMovieState
import com.tes.assessment.persentation.detail.review.ReviewMovieState
import com.tes.assessment.persentation.detail.trailer.TrailerMovieState
import com.tes.assessment.utils.Constants
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase,
    private val creditUseCase: CreditUseCase,
    private val reviewUseCase: ReviewUseCase,
    private val trailerUseCase: TrailerUseCase
) : ViewModel() {
    private val _listDetailMovieData = MutableStateFlow(DetailMovieState())
    var listDetailMovieData: StateFlow<DetailMovieState> = _listDetailMovieData

    private val _listCreditMovieData = MutableStateFlow(CreditMovieState())
    var listCreditMovieData: StateFlow<CreditMovieState> = _listCreditMovieData

    private val _listReviewMovieData = MutableStateFlow(ReviewMovieState())
    var listReviewMovieData: StateFlow<ReviewMovieState> = _listReviewMovieData

    private val _listTrailerMovieData = MutableStateFlow(TrailerMovieState())
    var listTrailerMovieData: StateFlow<TrailerMovieState> = _listTrailerMovieData

    fun getDetailMovie(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        detailMovieUseCase(movieId, Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listDetailMovieData.value =
                        DetailMovieState(detailList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listDetailMovieData.value = DetailMovieState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listDetailMovieData.value =
                        DetailMovieState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }

    fun getCreditMovie(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        creditUseCase(movieId, Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listCreditMovieData.value =
                        CreditMovieState(creditList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listCreditMovieData.value = CreditMovieState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listCreditMovieData.value =
                        CreditMovieState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }

    fun getReviewMovie(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        reviewUseCase(movieId, Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listReviewMovieData.value =
                        ReviewMovieState(reviewList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listReviewMovieData.value = ReviewMovieState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listReviewMovieData.value =
                        ReviewMovieState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }

    fun getTrailerMovie(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        trailerUseCase(movieId, Constants.API_KEY_TMDB, Constants.LANGUAGE).collect {
            when (it) {
                is ResultState.Success -> {
                    _listTrailerMovieData.value =
                        TrailerMovieState(trailerList = it.data ?: emptyList())
                }
                is ResultState.Loading -> {
                    _listTrailerMovieData.value = TrailerMovieState(isLoading = true)
                }
                is ResultState.Error -> {
                    _listTrailerMovieData.value =
                        TrailerMovieState(error = it.message ?: "An Unexpected Error")
                }
            }
        }
    }
}