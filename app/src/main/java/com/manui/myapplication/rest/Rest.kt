package com.manui.myapplication.rest

import com.google.gson.GsonBuilder
import com.manui.myapplication.BuildConfig
import com.manui.myapplication.constants.Constants.SDF_FORMAT
import com.manui.myapplication.rest.networkadapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Rest {

    companion object {

        private var retrofit: Retrofit? = null
        private var api: RestInterface? = null

        private const val REQUEST_TIMEOUT = 20
        private var token: String = ""
        private val GSON = GsonBuilder().setDateFormat(SDF_FORMAT).create()
        private var okHttpClient: OkHttpClient? = null


        fun getAuth(): RestInterface {
            initOkHttp()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(GSON))
                    .addCallAdapterFactory(NetworkResponseAdapterFactory())
                    .build()

                api = retrofit!!.create(RestInterface::class.java)
            }
            return api!!
        }


        private fun initOkHttp() {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY


            httpClient.addInterceptor(interceptor)

            okHttpClient = httpClient.build()
        }
    }
}
