package com.esgi.students.camerax.bo

data class Recipe(
    var description: String = "",
    var id: Int = -1,
    var ingredientsId: Int = -1,
    var level: String = "",
    var name: String = "",
    var photo: String = "",
    var timeToCook: String = "",
    var timeToPrepare: String = ""
)
