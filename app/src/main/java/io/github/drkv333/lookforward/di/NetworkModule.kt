package io.github.drkv333.lookforward.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.drkv333.lookforward.network.CalendarificAPIKeyInterceptor
import io.github.drkv333.lookforward.network.CalendarificService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(CalendarificAPIKeyInterceptor())
        .readTimeout(1, TimeUnit.DAYS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://calendarific.com/api/v2/")
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(MediaType.get("application/json")))
        .build()

    @Provides
    @Singleton
    fun provideCalendarificService(retrofit: Retrofit): CalendarificService = retrofit.create(CalendarificService::class.java)
}