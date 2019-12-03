package com.mpexabi.football.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    fun getUrl(): Retrofit {

        val retrofitApi: Retrofit?
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(log)

        retrofitApi = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        return retrofitApi!!
    }
}