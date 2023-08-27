package com.esgi.students.camerax.bo

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeIngredient(
    var _id: String = "",
    var mesure: String = "",
    var ingredient: Ingredient = Ingredient.placeholder(),
) {
    companion object {
        fun placeholder(): RecipeIngredient {
            return RecipeIngredient();
        }
    }

}

class RecipeIngredientDeserializer : JsonDeserializer<RecipeIngredient> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RecipeIngredient {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("_id")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        return RecipeIngredient(id, name)
    }
}
class ListOfRecipeIngredientDeserializer : JsonDeserializer<List<RecipeIngredient>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<RecipeIngredient> {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("_id")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        return listOf(RecipeIngredient(id, name))
    }
}
