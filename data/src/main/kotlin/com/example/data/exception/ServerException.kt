package com.example.data.exception

open class ServerException(
        val httpCode: Int,
        val serverErrorCode: String,
        val errorMessage: String) : Throwable("$httpCode $errorMessage")