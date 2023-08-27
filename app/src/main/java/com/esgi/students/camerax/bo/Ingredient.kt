package com.esgi.students.camerax.bo

class Ingredient(
    var _id: String = "",
    var name: String = "",
    var image: Media = Media.placeholder(),
) {
    companion object {
        fun placeholder(): Ingredient {
            return Ingredient();
        }
    }
}
