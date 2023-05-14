package com.example.jsoncompose.repository

import android.util.Log
import com.example.jsoncompose.data.model.JsonResponseItem
import com.example.jsoncompose.data.network.ApiService
import com.example.jsoncompose.data.wrapper.DataOrException
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 14.05.2023
 */

class QuestionsRepository @Inject constructor(private val api: ApiService) {

   private val dataOrException = DataOrException<ArrayList<JsonResponseItem>, Boolean, java.lang.Exception>()

   suspend fun getAllQuestions(): DataOrException<ArrayList<JsonResponseItem>, Boolean, java.lang.Exception> {
      try {
         dataOrException.loading = true
         dataOrException.data = api.getAllQuestions()
         if (dataOrException.data.toString()
                .isNotEmpty()) dataOrException.loading = false

      } catch (ex: Exception) {
         dataOrException.ex = ex
         Log.d("EXC", "getAllQuestions: ${dataOrException.ex!!.localizedMessage}")
      }
      return dataOrException
   }
}