package com.riansap.submissiondicoding.network

import com.riansap.submissiondicoding.model.Fruit
import retrofit2.http.GET

/**
 * ApiService
 * Sebuah interface yang digunakan untuk mendefinisikan endpoint dari API.
 *
 * Endpoint tersedia:
 * 1. `GET /fruits/en`: Mengambil daftar buah dalam bahasa Inggris.
 */
interface ApiService {

    /**
     * Mengambil daftar buah dari endpoint API.
     *
     * @return List<Fruit> Daftar buah dalam format JSON yang didefinisikan sebagai model [Fruit].
     * @throws Exception jika ada kesalahan selama proses pemanggilan API.
     */
    @GET("fruits/en")
    suspend fun getFruits(): List<Fruit>
}
