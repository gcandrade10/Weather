package com.example.weathersample

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiFactory {
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val builder = OkHttpClient().newBuilder().addInterceptor(authInterceptor)

    private fun retrofit(): Retrofit {
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        val client = builder.build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.metaweather.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: Api = retrofit().create(Api::class.java)
}

interface Api {
    @GET("/api/location/search")
    suspend fun search(@Query("query") query: String): Response<List<Result>>


    @GET("/api/location/{id}")
    suspend fun forecast(@Path("id") id: Int): Response<ForecastResponse>
}

data class Result(
    val title: String,
    @field:Json(name = "location_type")
    val type: String,
    @field:Json(name = "woeid")
    val id: Int
)

data class ForecastResponse(
    val title: String,
    @field:Json(name = "consolidated_weather")
    val list: List<Forecast>
)

data class Forecast(
    @field:Json(name = "weather_state_name")
    val name: String,
    @field:Json(name = "weather_state_abbr")
    val icon: String,
    @field:Json(name = "the_temp")
    val temperature: Double,
    @field:Json(name = "min_temp")
    val minTemperature: Double,
    @field:Json(name = "max_temp")
    val maxTemperature: Double,
    @field:Json(name = "applicable_date")
    val date: String
)