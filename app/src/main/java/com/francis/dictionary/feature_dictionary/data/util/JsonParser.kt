package com.francis.dictionary.feature_dictionary.data.util

import java.lang.reflect.Type

/**
 * get an object from  json string and pass an object to json string
 */
interface JsonParser {

    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(obj: T, type: Type): String?
}
