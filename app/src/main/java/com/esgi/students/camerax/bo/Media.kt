package com.esgi.students.camerax.bo

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class Media(
    var _id: String = "",
    var filename: String = "",
    var mimeType: String = "",
    var base64payload: String = "",

    var mediaCategories: List<MediaCategory> = emptyList(),
    var accessUrl: String = "",
) {

    companion object {
        fun placeholder(): Media {
            return Media();
        }
    }
}

class MediaDeserializer : JsonDeserializer<Media> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Media {
        val jsonObject = json.asJsonObject
        val _id = jsonObject?.get("_id")?.asString ?: ""
        val filename = jsonObject?.get("filename")?.asString ?: ""
        val mimeType = jsonObject?.get("filename")?.asString ?: ""
        val base64payload = jsonObject?.get("filename")?.asString ?: ""
        val mediaCategories = jsonObject?.getAsJsonArray("mediaCategories") ?: JsonArray()
        val accessUrl = jsonObject?.get("filename")?.asString ?: ""

        val gson = GsonBuilder()
            .registerTypeAdapter(MediaCategory::class.java, MediaCategoryDeserializer())
            .create()

        val mediaCategoriesD = mediaCategories.map {
            gson.fromJson(it, MediaCategory::class.java)
        }

        return Media(
            _id = _id,
            filename = filename,
            mimeType = mimeType,
            base64payload = base64payload,
            mediaCategories = mediaCategoriesD,
            accessUrl = accessUrl
            )
    }
}
