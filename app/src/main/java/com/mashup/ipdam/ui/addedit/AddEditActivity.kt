package com.mashup.ipdam.ui.addedit

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.mashup.base.BaseActivity
import com.mashup.ipdam.R
import com.mashup.ipdam.databinding.ActivityAddEditBinding
import com.mashup.ipdam.extension.toStringList
import com.mashup.ipdam.extension.toUriList
import com.mashup.ipdam.ui.addedit.adapter.PointAdapter
import com.mashup.ipdam.ui.addedit.adapter.area.AddAmenitiesAdapter
import com.mashup.ipdam.ui.addedit.adapter.area.AmenitiesAdapter
import com.mashup.ipdam.ui.addedit.adapter.image.AddRoomImageAdapter
import com.mashup.ipdam.ui.addedit.adapter.image.RoomImageAdapter
import com.mashup.ipdam.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedRxImagePicker

@AndroidEntryPoint
class AddEditActivity : BaseActivity<ActivityAddEditBinding>(R.layout.activity_add_edit) {
    override var logTag: String = "add_editActivity"
    private val viewModel: AddEditViewModel by viewModels()

    private val searchActivityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.run {
                    viewModel.setReviewAddress(getStringExtra("address") ?: "")
                }
            }
        }

    override fun initLayout() {
        binding.viewModel = viewModel

        initPointRecyclerView()
        initAreaRecyclerView()
        initImageRecyclerView()
    }

    private fun initPointRecyclerView() {
        binding.addEditPointRecyclerView.apply {
            adapter = PointAdapter(viewModel)
        }
    }

    private fun initAreaRecyclerView() {
        binding.addEditAreaRecyclerView.apply {
            val addAdapter = AddAmenitiesAdapter(viewModel)
            val areaAdapter = AmenitiesAdapter(viewModel)
            adapter = ConcatAdapter(areaAdapter, addAdapter)
        }
    }

    private fun initImageRecyclerView() {
        binding.addEditImageRecyclerView.apply {
            addItemDecoration(RoomImageItemDecoration(context))

            val addAdapter = AddRoomImageAdapter(viewModel)
            val imageAdapter = RoomImageAdapter(viewModel)
            adapter = ConcatAdapter(addAdapter, imageAdapter)
        }
    }


    override fun observeViewModel() {
        viewModel.addReviewImageEvent.observe(this) {
            val selectedImageList = viewModel.roomImageList.value
            showImagePicker(selectedImageList)
        }
        viewModel.addReviewAreaEvent.observe(this) {
            //TODO 주변 상권 추가
        }
        viewModel.reviewType.observe(this) {
            if (it == AddEditType.ADD) {
                showAddTypeView()
            } else {
                showEditTypeView()
            }
        }
        viewModel.showSearchResultEvent.observe(this) {
            showSearchActivity(viewModel.reviewAddress.value ?: "")
        }
    }

    private fun showAddTypeView() {
        binding.apply {
            addEditTitle.text = getString(R.string.add_title)
            addEditSaveButton.text = getString(R.string.add_save_button)
        }
    }

    private fun showSearchActivity(searchingAddress: String) {
        val intent = Intent(this, SearchActivity::class.java).apply {
            putExtra("keyword", searchingAddress)
        }
        searchActivityResultLauncher.launch(intent)
    }

    private fun showEditTypeView() {
        binding.apply {
            addEditTitle.text = getString(R.string.edit_title)
            addEditSaveButton.text = getString(R.string.edit_save_button)
        }
    }

    private fun showImagePicker(selectedImageList: List<String>?) {
        TedRxImagePicker.with(this)
            .selectedUri(selectedImageList?.toUriList())
            .buttonBackground(R.drawable.bg_corner_solid)
            .max(5, R.string.error_too_much_selected_image)
            .startMultiImage()
            .subscribe(
                { uriList ->
                    viewModel.setRoomImage(uriList.toStringList())
                },
                {
                    Log.e(logTag, it.stackTraceToString())
                }
            ).addToDisposable()
    }
}