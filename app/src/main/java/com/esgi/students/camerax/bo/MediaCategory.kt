package com.esgi.students.camerax.bo

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MediaCategory(var _id: String = "", var name: String = "") {
    companion object {
        fun placeholder(): MediaCategory {
            return MediaCategory("", "")
        }
    }
}

class MediaCategoryDeserializer : JsonDeserializer<MediaCategory> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MediaCategory {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("_id")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        return MediaCategory(id, name)
    }
}
