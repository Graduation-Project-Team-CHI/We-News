package com.nourelden515.wenews.base

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract val TAG: String

    protected open fun log(message: String) {
        Log.v(TAG, message)
    }
}