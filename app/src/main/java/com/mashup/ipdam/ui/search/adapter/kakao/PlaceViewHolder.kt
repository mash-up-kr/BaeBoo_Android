package com.mashup.ipdam.ui.search.adapter.kakao

import androidx.recyclerview.widget.RecyclerView
import com.mashup.ipdam.databinding.ItemPlaceBinding
import com.mashup.ipdam.entity.kakao.Places

class PlaceViewHolder(
    val binding: ItemPlaceBinding,
    private val placeClickListener: PlaceAdapter.PlaceClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            placeClickListener.onPlaceClick(bindingAdapterPosition)
        }
    }

    fun bind(document: Places) {
        binding.document = document
    }
}