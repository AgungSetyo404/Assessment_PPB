package org.d3if2082.task_projecta.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2082.task_projecta.models.Obat
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/AgungSetyo404/Assessment_PPB/master/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ObatApiService {
    @GET("obatData.json")
    suspend fun getObat(): List<Obat>
}

object ObatApi {
    val service: ObatApiService by lazy {
        retrofit.create(ObatApiService::class.java) }

    fun getObatURL(namaObat: String): String{
        return "$BASE_URL$namaObat.png"
    }
}
