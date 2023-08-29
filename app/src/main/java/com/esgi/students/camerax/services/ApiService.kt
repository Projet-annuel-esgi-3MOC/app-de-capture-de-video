package com.esgi.students.camerax.services;

import com.esgi.students.camerax.bo.Challenge
import com.esgi.students.camerax.bo.Participation
import com.esgi.students.camerax.bo.Recipe;

import retrofit2.http.GET;
import retrofit2.http.POST
import retrofit2.http.Path

data class BusinessResponse<T>(
    val ok: Boolean,
    val status: String,
    val payload: T?,
)

public interface ApiService {
    @GET("/crud/recipe")
    suspend fun getRecipes(): List<Recipe>
    @GET("/crud/challenge")
    suspend fun getChallenges(): List<Challenge>
    @POST("android/participate-challenge/{challengeId}")
    suspend fun addChallengeParticipation(@Path("challengeId") parameter: String): BusinessResponse<Participation>
}
