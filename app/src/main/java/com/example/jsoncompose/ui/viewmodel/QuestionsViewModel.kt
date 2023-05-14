package com.example.jsoncompose.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsoncompose.data.model.JsonResponseItem
import com.example.jsoncompose.data.wrapper.DataOrException
import com.example.jsoncompose.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 14.05.2023
 */

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionsRepository) : ViewModel() {

   val data: MutableState<DataOrException<ArrayList<JsonResponseItem>, Boolean, Exception>> =
       mutableStateOf(DataOrException(null, true, Exception("")))

   init {
      getAllQuestions()
   }

   private fun getAllQuestions() {
      viewModelScope.launch {
         data.value.loading = true
         data.value = repository.getAllQuestions()
         if (data.value.data.toString().isNotEmpty()){
            data.value.loading = false
         }
      }
   }
}