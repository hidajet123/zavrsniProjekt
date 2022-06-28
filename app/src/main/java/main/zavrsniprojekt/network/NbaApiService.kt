package main.zavrsniprojekt.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttp = OkHttpClient.Builder().addInterceptor(logger)


private const val BASE_URL = "https://free-nba.p.rapidapi.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttp.build())
        .baseUrl(BASE_URL)
        .build()

interface NbaApiService {
    @Headers(
            "X-RapidAPI-Key: 98f710a208msh4f8127e29d54ac0p12eccajsne3b620d1ecdf",
            "X-RapidAPI-Host: free-nba.p.rapidapi.com"
    )
    @GET("players")
    suspend fun getPlayersByName(@Query("search",encoded = true)search : String): NbaProperty

}
object NbaApi {
    val retrofitService : NbaApiService by lazy { retrofit.create(NbaApiService::class.java) }
}
