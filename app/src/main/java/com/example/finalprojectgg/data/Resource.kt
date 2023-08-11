package com.example.finalprojectgg.data

sealed class Resource<T> {
    class Success<T>(val data: T):Resource<T>()
    class Empty<T>() : Resource<T>()
    class Error<T>(val message: String):Resource<T>()
    class Loading<T>(val isLoading: Boolean = true):Resource<T>()
}
