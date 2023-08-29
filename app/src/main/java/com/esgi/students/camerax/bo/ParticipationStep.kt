package com.esgi.students.camerax.bo

class ParticipationStep(
    var _id: String = "",
    var comment: String = "",
    var rating: String = "",
    var doneIn: Int = -1,
    var recipeStep: RecipeStep = RecipeStep(),
) {
}