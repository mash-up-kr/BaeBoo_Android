package com.mashup.ipdam.ui.addedit.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.ipdam.databinding.ItemImageBinding
import com.mashup.ipdam.ui.addedit.AddEditViewModel

class RoomImageAdapter(
    private val viewModel: AddEditViewModel
) : ListAdapter<String, RoomImageHolder>(RoomImageDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomImageHolder {
        return RoomImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            viewModel
        )
    }

    override fun onBindViewHolder(holder: RoomImageHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RoomImageHolder(
    private val binding: ItemImageBinding,
    private val viewModel: AddEditViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.apply {
            this.imageUrl = imageUrl
            imageDelete.setOnClickListener {
                viewModel.deleteReviewImage(bindingAdapterPosition)
            }
        }
    }
}


object RoomImageDiffUtilCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}