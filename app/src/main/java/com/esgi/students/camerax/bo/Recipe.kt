package com.esgi.students.camerax.bo

class Recipe(
    var _id: String = "",
    var name: String = "",
    var description: String = "",
    var difficulty: String = "",
    var recipeIngredients: List<RecipeIngredient> = emptyList(),
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