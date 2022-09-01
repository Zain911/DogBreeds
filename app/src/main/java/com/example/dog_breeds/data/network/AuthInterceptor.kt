package com.example.dog_breeds.data.network


import okhttp3.Interceptor

class AuthInterceptor : Interceptor {



    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}