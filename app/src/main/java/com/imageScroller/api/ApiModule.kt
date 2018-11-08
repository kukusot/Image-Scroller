package com.imageScroller.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imageScroller.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    private const val API_URL = "https://api.shutterstock.com/v2/"
    private const val CLIENT_KEY = "f1431-142e3-99d05-bf119-8647a-b4500"
    private const val CLIENT_SECRET = "5e569-1fc94-5cb65-2dfa0-d916a-826b0"

    @JvmStatic
    @Singleton
    @Provides
    fun provideHttpClient(gson: Gson): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
            addInterceptor {
                val credentials = Credentials.basic(CLIENT_KEY, CLIENT_SECRET)
                it.proceed(it.request().newBuilder().header("Authorization", credentials).build())
            }
        }
        return Retrofit.Builder().apply {
            baseUrl(API_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(okHttpBuilder.build())
        }.build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()!!

    @JvmStatic
    @Singleton
    @Provides
    fun provideImagesService(retrofit: Retrofit) = retrofit.create(ImagesService::class.java)!!

}