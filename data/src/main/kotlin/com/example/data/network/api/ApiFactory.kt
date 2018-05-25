package com.example.data.network.api

import com.example.data.data.BuildConfig
import com.example.data.network.ClientFactory
import com.example.data.network.GsonFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiFactory @Inject constructor(private val clientFactory: ClientFactory) {
    val access = create(Api::class.java)

    private fun <T> create(clazz: Class<T>): T {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER)
                .client(clientFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.networkGson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(clazz)
    }
}
