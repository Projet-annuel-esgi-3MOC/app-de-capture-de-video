package com.esgi.students.camerax.services;

import com.esgi.students.camerax.bo.Challenge
import com.esgi.students.camerax.bo.Recipe;

import retrofit2.http.GET;

public interface ApiService {
    @GET("/recipe")
    suspend fun getRecipes(): List<Recipe>
    @GET("/challenge")
    suspend fun getChallenges(): List<Challenge>
}
