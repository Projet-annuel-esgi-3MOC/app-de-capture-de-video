package com.esgi.students.camerax.bo

class Participation(
    var _id: String = "",
    var comment: String = "",
    var ipAddress: String = "",
    var podium: Int = -1,
    var steps: List<ParticipationStep> = emptyList(),
    var recipe: Recipe? = null,
    var recipeName: String? = null,
    var recipeImage: String? = null,
) {
}