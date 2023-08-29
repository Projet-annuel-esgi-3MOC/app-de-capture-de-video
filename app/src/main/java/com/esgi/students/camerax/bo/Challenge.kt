package com.esgi.students.camerax.bo

class Challenge(
    var _id: String = "",
    var recipe: Recipe = Recipe(),
    var maxParticipants: Int = 0,
    var participations: List<Participation> = emptyList(),
) {
}
