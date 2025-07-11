package com.example.composemovieapp.movieslist

import androidx.annotation.StringRes
import com.example.composemovieapp.R

enum class ErrorMessage(@StringRes val message: Int) {
    INTERNET_CONNECTION(R.string.internet_connection_error_message),
    DEFAULT(R.string.default_error_message_text)
}