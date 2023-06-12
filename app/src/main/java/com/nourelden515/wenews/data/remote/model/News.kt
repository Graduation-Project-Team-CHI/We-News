package com.nourelden515.wenews.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
):Parcelable{
    @Parcelize
    data class Source(
        val id: String? = "",
        val name: String? = ""
    ):Parcelable
}