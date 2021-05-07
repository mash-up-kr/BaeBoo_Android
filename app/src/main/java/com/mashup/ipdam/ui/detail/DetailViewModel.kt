package com.mashup.ipdam.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.mashup.base.BaseViewModel
import com.mashup.ipdam.data.review.Review
import com.mashup.ipdam.data.review.ReviewImage
import com.mashup.ipdam.entity.review.PointType
import com.mashup.ipdam.entity.review.ReviewPoint
import com.mashup.ipdam.entity.review.ReviewType
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    override var logTag: String = "DetailViewModel"

    private val _review = MutableLiveData<Review>()
    val review: LiveData<Review> = _review

    private val _reviewPoints = MutableLiveData<List<ReviewPoint>>()
    val reviewPoints: LiveData<List<ReviewPoint>> = _reviewPoints

    fun setReview(review: Review?) {
        review?.let {
            _review.value = it
            _reviewPoints.value = listOf(
                ReviewPoint(ReviewType.HOST, PointType.from(it.owner ?: -1)),
                ReviewPoint(ReviewType.SAFETY, PointType.from(it.safety ?: -1)),
                ReviewPoint(ReviewType.DISTANCE, PointType.from(it.distance ?: -1)),
                ReviewPoint(ReviewType.CLEANNESS, PointType.from(it.clean ?: -1))
            )
        }
    }
}