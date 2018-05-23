package com.example.data.network

import com.example.data.data.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ClientFactory {

    private val CONNECT_TIMEOUT_MILLIS = 120000L
    private val READ_TIMEOUT_MILLIS = 120000L


    fun create(): OkHttpClient {
        val builder = getPreconfiguredClientBuilder()
            .addInterceptor(getLoggingInterceptor())

        return builder.build()
    }

    private fun getPreconfiguredClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        }
    }

    private fun getLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .request("Request")
            .response("Response")
            .build()
    }
}