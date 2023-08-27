package com.esgi.students.camerax.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.services.ApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class HomeViewModel() : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun fetchRecipes() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://intermediaire-node.onrender.com")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        viewModelScope.launch() {

            try {
                Log.d("toto", "start retrofit")
                val response = apiService.getRecipes()
                Log.d("toto", "response $response")

                _recipes.value = response
            } catch (e: Exception) {
                Log.e("toto", "$e")
            }
        }
    }
}
