package com.esgi.students.camerax.bo

class Challenge(
    var _id: String = "",
    var recipe: Recipe = Recipe.placeholder(),
    var maxParticipants: Int = 0,
) {
    companion object {
        fun placeholder(): Ingredient {
            return Ingredient();
        }
    }
}
