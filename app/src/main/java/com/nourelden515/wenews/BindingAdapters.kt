package com.nourelden515.wenews

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:showWhenLoading"])
fun showWhenLoading(view: View, state: Boolean) {
    if (state) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:hideWhenLoading"])
fun hideWhenLoading(view: View, state: Boolean) {
    if (state) {
        view.visibility = View.INVISIBLE
    } else {
        view.visibility = View.VISIBLE
    }
}