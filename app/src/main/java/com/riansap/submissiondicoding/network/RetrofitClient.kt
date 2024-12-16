package com.riansap.submissiondicoding.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RetrofitClient
 * Sebuah singleton object yang menyediakan instance Retrofit untuk mengakses API.
 *
 * Properti:
 * - `BASE_URL`: URL dasar untuk API yang akan digunakan.
 * - `apiService`: Instance dari interface [ApiService] untuk melakukan pemanggilan endpoint.
 */
object RetrofitClient {

    // URL dasar untuk API
    private const val BASE_URL = "https://api.api-onepiece.com/v2/"

    /**
     * Instance singleton Retrofit yang digunakan untuk memanggil API melalui interface [ApiService].
     * Properti ini menggunakan lazy initialization sehingga hanya akan diinisialisasi saat pertama kali diakses.
     */
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Tetapkan URL dasar
            .addConverterFactory(GsonConverterFactory.create()) // Tambahkan konverter Gson untuk parsing JSON
            .build() // Bangun instance Retrofit
            .create(ApiService::class.java) // Buat implementasi dari interface ApiService
    }
}
