package com.android.person.util

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.android.person.R

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, observer: (T) -> Unit) {
    liveData?.observe(this, Observer(observer))
}

fun Context.showErrorDialog(errorMessage: String, refreshClickListener: () -> Unit) {
    AlertDialog.Builder(this).apply {
        setMessage(errorMessage)
        setCancelable(false)
        setPositiveButton(getText(R.string.refresh)) { _, _ ->
            refreshClickListener.invoke()
        }
        create().show()
    }
}