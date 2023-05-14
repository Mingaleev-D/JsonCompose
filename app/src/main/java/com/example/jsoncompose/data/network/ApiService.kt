package com.example.jsoncompose.data.network

import com.example.jsoncompose.common.Constants.ENDPOINT
import com.example.jsoncompose.data.model.JsonResponse
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 14.05.2023
 */

interface ApiService {

   @GET(ENDPOINT)
   suspend fun getAllQuestions(): JsonResponse
}