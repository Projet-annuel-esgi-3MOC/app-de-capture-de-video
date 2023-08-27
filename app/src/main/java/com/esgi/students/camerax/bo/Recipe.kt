package com.esgi.students.camerax.bo

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

class Recipe(
    var _id: String = "",
    var name: String = "",
    var description: String = "",
    var difficulty: String = "",
    var recipeIngredients: List<String> = emptyList(),
    @JsonAdapter(MediaDeserializer::class)
    var image: Media = Media.placeholder(),
    var timeToCook: String = "",
    var timeToPrepare: String = ""
) {

    companion object {
        fun placeholder(): Recipe {
            return Recipe();
        }
    }
}

class RecipeDeserializer : JsonDeserializer<Recipe> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Recipe {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("_id")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        val description = jsonObject?.get("description")?.asString ?: ""
        val difficulty = jsonObject?.get("difficulty")?.asString ?: ""
        val recipeIngredientsJsonArray = jsonObject?.getAsJsonArray("recipeIngredients") ?: emptyList()
        val imageJson = jsonObject?.get("image")?.asString ?: ""
        val timeToCook = jsonObject?.get("timeToCook")?.asString ?: ""
        val timeToPrepare = jsonObject?.get("timeToPrepare")?.asString ?: ""

        val gson = GsonBuilder()
            .registerTypeAdapter(RecipeIngredient::class.java, RecipeIngredientDeserializer())
            .registerTypeAdapter(Media::class.java, MediaDeserializer())
            .create()

        val recipeIngredients = recipeIngredientsJsonArray.map { gson.fromJson(it, RecipeIngredient::class.java) }
        val image = gson.fromJson(imageJson, Media::class.java)

        return Recipe(
            _id = id,
            name = name,
            description = description,
            difficulty = difficulty,
            //recipeIngredients = recipeIngredients,
            image = image,
            timeToCook = timeToCook,
            timeToPrepare = timeToPrepare,
        )
    }
}
