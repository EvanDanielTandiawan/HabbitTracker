package com.example.habittracker.data

/**
 * A generic class that holds a value with its loading status.
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}