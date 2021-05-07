package com.mashup.ipdam.ui.search

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mashup.ipdam.R

@BindingAdapter("keyword")
fun setPlaceHeader(view: TextView, keyword: String) {
    view.text = view.context.getString(
        R.string.search_place_header,
        keyword
    )
}