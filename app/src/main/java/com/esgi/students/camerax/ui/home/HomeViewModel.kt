package com.esgi.students.camerax.ui.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.esgi.students.camerax.bo.Media
import com.esgi.students.camerax.bo.MediaCategory
import com.esgi.students.camerax.bo.MediaCategoryDeserializer
import com.esgi.students.camerax.bo.MediaDeserializer
import com.esgi.students.camerax.bo.Recipe
import com.esgi.students.camerax.bo.RecipeIngredient
import com.esgi.students.camerax.services.ApiService
import com.esgi.students.camerax.services.JsonHttpFetcher
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import kotlin.coroutines.resumeWithException

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes



    fun fetchRecipes() {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val gson = GsonBuilder()
            .registerTypeAdapter(MediaCategory::class.java, MediaCategoryDeserializer())
            .registerTypeAdapter(Media::class.java, MediaDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://intermediaire-node.onrender.com")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        /*val url = "http://localhost:3000/projects"
        val recipesList = ArrayList<Recipe>()

        val jsonArrayRequest = JsonArrayRequest(url,
            {  response ->
                for (i in 0 until response.length()) {
                    try {
                        val jsonObject = response.getJSONObject(i)
                        val recipe = Recipe()
                        recipe.name = (jsonObject.getString("name"))
                        recipe.description = (jsonObject.getString("description"))
                        recipesList.add(recipe)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            },
            {
                fun onErrorResponse(error: VolleyError) {
                    Log.e("Volley", error.toString())
                }
            })
        val requestQueue = Volley.newRequestQueue(getApplication())
        requestQueue.add(jsonArrayRequest)
*/

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
