package com.nourelden515.wenews.utils

object Constants {
    const val PREDICTION_BASE_URL = "http://ec2-13-51-56-97.eu-north-1.compute.amazonaws.com:8080/"
    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    enum class Category{
        GENERAL,BUSINESS,HEALTH,SCIENCE,SPORTS,TECHNOLOGY
    }
}