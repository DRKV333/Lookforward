package io.github.drkv333.lookforward.network

import okhttp3.Interceptor
import okhttp3.Response

class CalendarificAPIKeyInterceptor: Interceptor {
    private val key = "b748236ed929be534522cca02588fccc0df0628e"

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request().newBuilder()
            .url(
                chain.request().url().newBuilder()
                    .addQueryParameter("api_key", key)
                    .build()
            )
            .build()
    )
}