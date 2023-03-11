package com.francis.dictionary.feature_dictionary.domain.model

/**
 * What we want to show in our UI
 */
data class Definition (
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
    )