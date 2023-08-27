package com.esgi.students.camerax.bo

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
