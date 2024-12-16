package com.riansap.submissiondicoding.repository

import android.util.Log
import com.riansap.submissiondicoding.model.Fruit
import com.riansap.submissiondicoding.network.RetrofitClient

class FruitRepository {
    private val apiService = RetrofitClient.apiService
    suspend fun getFruits(): List<Fruit> {
        try {
            val response = apiService.getFruits()
            Log.d("FruitRepository", "API Response: $response")
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
            Log.e("FruitRepository", "Error fetching fruits", e)
            throw e
        }
    }

}