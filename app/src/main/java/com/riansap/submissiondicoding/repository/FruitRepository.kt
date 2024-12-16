package com.riansap.submissiondicoding.repository

import android.util.Log
import com.riansap.submissiondicoding.model.Fruit
import com.riansap.submissiondicoding.network.RetrofitClient

/**
 * FruitRepository
 * Sebuah kelas repository yang bertanggung jawab untuk mengelola pengambilan data buah dari API.
 *
 * Repository ini bertindak sebagai lapisan abstraksi antara data sumber (API) dan ViewModel.
 */
class FruitRepository {

    // Instance ApiService dari RetrofitClient
    private val apiService = RetrofitClient.apiService

    /**
     * Mengambil daftar buah dari API.
     *
     * @return List<Fruit> Daftar buah yang sudah dipetakan ke dalam model data.
     * @throws Exception jika ada kesalahan selama proses pengambilan data.
     */
    suspend fun getFruits(): List<Fruit> {
        try {
            // Memanggil API melalui Retrofit
            val response = apiService.getFruits()

            // Log untuk debug: Menampilkan raw data yang diterima dari API
            Log.d("FruitRepository", "API Response: $response")

            // Memetakan raw data dari API ke dalam model Fruit
            return response.map {
                Fruit(
                    id = it.id,
                    name = it.name,
                    roman_name = it.roman_name,
                    type = it.type,
                    filename = it.filename,
                    description = it.description
                )
            }
        } catch (e: Exception) {
            // Log untuk menangani error
            Log.e("FruitRepository", "Error fetching fruits", e)
            // Throw exception agar dapat ditangani di lapisan ViewModel
            throw e
        }
    }
}
