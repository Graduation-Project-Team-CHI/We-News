package com.nourelden515.wenews.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, uiState: UiState<T>?) {
    if (uiState is UiState.Loading) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:showWhenError")
fun <T> showWhenError(view: View, uiState: UiState<T>?) {
    if (uiState is UiState.Error) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, uiState: UiState<T>?) {
    if (uiState is UiState.Success) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter(value = ["app:result"])
fun getResult(textView: TextView, result: String?) {
    val percentage = result?.toFloatOrNull()?.times(100)
    if (percentage != null) {
        if (percentage >= 50) {
            textView.text = String.format("Real News\n%.1f%%", percentage)
        } else {
            textView.text = String.format("Fake News\n%.1f%%", 100 - percentage)
        }
    }

}