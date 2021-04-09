package com.mashup.ipdam.ui.register

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.mashup.base.BaseActivity
import com.mashup.ipdam.R
import com.mashup.ipdam.databinding.ActivityRegisterBinding
import com.mashup.ipdam.ui.register.RegisterInputType.*

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    override var logTag: String = "RegisterActivity"
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun initLayout() {
        binding.apply {
            viewModel = registerViewModel
            registerBackButton.setOnClickListener {
                finish()
            }
            registerButton.setOnClickListener {
                //TODO: 회원가입 로직 작성
            }
        }
        initSearchEditText()
    }

    private fun initSearchEditText() {
        binding.registerSearchButton.setOnClickListener {
            showSearchSchoolView()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        registerViewModel.idInputType.observe(this) { type ->
            when (type) {
                SAFE -> showIdSuccessLayout()
                WRONG -> showIdFailedLayout()
                else -> {
                }
            }
        }
        registerViewModel.passwordInputType.observe(this) { type ->
            when (type) {
                SAFE -> showPasswordSuccessLayout()
                WRONG -> showPasswordFailedLayout()
                else -> {
                }
            }
        }
        registerViewModel.passwordCheckInputType.observe(this) { type ->
            when (type) {
                SAFE -> showPasswordCheckSuccessLayout()
                WRONG -> showPasswordCheckFailedLayout()
                else -> {
                }
            }
        }
        registerViewModel.requestSearchSchool.observe(this) {
            showSearchSchoolView()
        }
    }

    private fun showSearchSchoolView() {
        //TODO: 학교 검색 화면
    }

    private fun showIdSuccessLayout() {
        binding.registerIdResult.run {
            text = getString(R.string.register_id_result_success)
            setTextColor(ContextCompat.getColor(context, R.color.primary_color))
        }
    }

    private fun showIdFailedLayout() {
        binding.registerIdResult.run {
            text = getString(R.string.register_id_result_failed)
            setTextColor(ContextCompat.getColor(context, R.color.red_color))
        }
    }

    private fun showPasswordSuccessLayout() {
        binding.registerPasswordResult.run {
            text = getString(R.string.register_password_result_success)
            setTextColor(ContextCompat.getColor(context, R.color.primary_color))
        }
    }

    private fun showPasswordFailedLayout() {
        binding.registerPasswordResult.run {
            text = getString(R.string.register_password_result_failed)
            setTextColor(ContextCompat.getColor(context, R.color.red_color))
        }
    }

    private fun showPasswordCheckSuccessLayout() {
        binding.registerPasswordCheckResult.run {
            text = getString(R.string.register_password_check_result_success)
            setTextColor(ContextCompat.getColor(context, R.color.primary_color))
        }
    }

    private fun showPasswordCheckFailedLayout() {
        binding.registerPasswordCheckResult.run {
            text = getString(R.string.register_password_check_result_failed)
            setTextColor(ContextCompat.getColor(context, R.color.red_color))
        }
    }
}