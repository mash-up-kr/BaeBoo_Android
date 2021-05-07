package com.mashup.ipdam.ui.addedit.adapter.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mashup.ipdam.R
import com.mashup.ipdam.databinding.ItemImageBinding
import com.mashup.ipdam.ui.addedit.AddEditViewModel

class AddRoomImageAdapter(
    private val viewModel: AddEditViewModel
) : RecyclerView.Adapter<AddRoomImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRoomImageViewHolder {
        return AddRoomImageViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: AddRoomImageViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}

class AddRoomImageViewHolder(
    private val binding: ItemImageBinding,
    private val viewModel: AddEditViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            root.setOnClickListener {
                viewModel.eventAddReviewImage()
            }
            imageDelete.visibility = View.INVISIBLE
            imageContent.apply {
                scaleType = ImageView.ScaleType.CENTER
                background = ContextCompat.getDrawable(context, R.drawable.bg_corner_solid)
                backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.photo_surface_color)
                setImageResource(R.drawable.ic_plus)
            }
        }
    }
}