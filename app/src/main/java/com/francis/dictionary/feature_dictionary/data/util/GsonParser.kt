package com.francis.dictionary.feature_dictionary.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * GSON is a Java library that is used for converting Java objects to and from JSON format.
 */
class GsonParser(
    private val gson: Gson
): JsonParser {

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }
}