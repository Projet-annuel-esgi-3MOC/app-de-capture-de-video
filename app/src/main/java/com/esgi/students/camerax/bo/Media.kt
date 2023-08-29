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
}
