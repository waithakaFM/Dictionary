package com.francis.dictionary.feature_dictionary.domain.use_case

import com.francis.dictionary.core.util.Resource
import com.francis.dictionary.feature_dictionary.domain.model.WordInfo
import com.francis.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    // Call the usecase as if it was an object while it is still a class
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if (word.isBlank()){
            return flow {  }
        }

        return repository.getWordInfo(word)
    }
}