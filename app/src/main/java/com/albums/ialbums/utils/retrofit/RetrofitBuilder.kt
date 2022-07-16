package com.albums.ialbums.utils.retrofit

import com.albums.ialbums.data.api.ApiService
import com.albums.ialbums.utils.Constants.Companion.ALBUM_LIST_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private val BASE_URL: String = ALBUM_LIST_URL

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    val apiService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

}