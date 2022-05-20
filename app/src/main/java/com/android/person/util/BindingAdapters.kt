package com.android.person.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("showIfTrue")
fun View.showIfTrue(bool: Boolean) {
    visibility = if (bool) {
        View.VISIBLE
    } else {
        View.GONE
    }
}