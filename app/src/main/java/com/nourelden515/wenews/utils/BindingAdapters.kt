package com.nourelden515.wenews.utils

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nourelden515.wenews.R
import com.nourelden515.wenews.ui.base.BaseAdapter
import java.util.Locale

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
    else view.visibility = View.INVISIBLE
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

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) {
    url?.let {
        Glide
            .with(view)
            .load(url)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .centerCrop().into(view)
    }
}

@BindingAdapter("app:dateTimeFormatted")
fun TextView.setDateTimeFormatted(dateTimeString: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())

    val date = inputFormat.parse(dateTimeString)
    val formattedDateTime = outputFormat.format(date)

    text = formattedDateTime
}