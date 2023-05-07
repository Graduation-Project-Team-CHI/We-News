package com.nourelden515.wenews.base

import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepository {
    fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<UiState<T?>> {
        return flow {
            emit(UiState.Loading)
            try {
                val result = function()
                if (result.isSuccessful) {
                    emit(UiState.Success(result.body()))
                } else {
                    emit(UiState.Error(result.message()))
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}