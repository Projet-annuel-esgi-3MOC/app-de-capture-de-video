package com.esgi.students.camerax.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.students.camerax.bo.Challenge
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.services.ApiService
import com.esgi.students.camerax.services.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class HomeViewModel() : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes
    private val _challenges = MutableLiveData<List<Challenge>>()
    val challenges: LiveData<List<Challenge>> = _challenges

    init {
        fetchRecipes()
        fetchChallenges()
    }

    private fun fetchRecipes() {
        viewModelScope.launch() {
            try {
                Log.d("toto", "start retrofit")
                val response = RetrofitInstance.apiService.getRecipes()
                Log.d("toto", "response $response")

                _recipes.postValue(response)
            } catch (e: Exception) {
                Log.e("toto", "$e")
            }
        }
    }
    private fun fetchChallenges() {
        viewModelScope.launch() {
            try {
                Log.d("toto", "start retrofit")
                val response = RetrofitInstance.apiService.getChallenges()
                Log.d("toto", "response $response")

                _challenges.postValue(response)
            } catch (e: Exception) {
                Log.e("toto", "$e")
            }
        }
    }
}
