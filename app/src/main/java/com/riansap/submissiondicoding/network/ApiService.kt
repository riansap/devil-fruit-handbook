package com.riansap.submissiondicoding.network

import com.riansap.submissiondicoding.model.Fruit
import retrofit2.http.GET

interface ApiService {
    @GET("fruits/en")
    suspend fun getFruits(): List<Fruit>
}
