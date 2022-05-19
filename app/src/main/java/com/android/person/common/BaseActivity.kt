package com.android.person.common

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.person.R
import com.android.person.enums.LoadingType
import com.android.person.util.observe

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    lateinit var binding: B

    private lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initObservers()
        initProgressDialog()
    }

    private fun initProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog.setContentView(R.layout.dialog_progress)
    }

    private fun initObservers() {
        getViewModel().apply {
            observe(progressLiveData) { type ->
                if (type == LoadingType.PROGRESS) {
                    progressDialog.show()
                } else {
                    progressDialog.dismiss()
                }
            }
        }
    }
}