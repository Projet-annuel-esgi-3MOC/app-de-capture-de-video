package com.esgi.students.camerax.bo

import com.google.gson.annotations.JsonAdapter

class Ingredient(
    var _id: String = "",
    var name: String = "",
    @JsonAdapter(MediaDeserializer::class)
    var image: Media = Media.placeholder(),
) {
    companion object {
        fun placeholder(): Ingredient {
            return Ingredient();
        }
    }
}
