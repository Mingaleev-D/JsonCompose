package com.example.jsoncompose.di

import com.example.jsoncompose.common.Constants.BASE_URL
import com.example.jsoncompose.data.network.ApiService
import com.example.jsoncompose.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   fun provideQuestionsApi(): ApiService {
      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(ApiService::class.java)
   }

   @Provides
   @Singleton
   fun provideQuestionsRepository(api: ApiService) = QuestionsRepository(api)
}