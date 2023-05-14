package com.example.jsoncompose.data.wrapper

import java.lang.Exception

data class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var ex: E? = null
)
