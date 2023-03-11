package com.francis.dictionary.feature_dictionary.data.repositoryImpl

import com.francis.dictionary.core.util.Resource
import com.francis.dictionary.feature_dictionary.data.local.WordInfoDao
import com.francis.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.francis.dictionary.feature_dictionary.domain.model.WordInfo
import com.francis.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    /**
     * Where our cashing logic is
     * Implement the principle of SSOT where all data displayed in the UI is not directly from API
     * rather from our Database
     */
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

        // Read the catched data in our database
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try{
            // Initiate api call
            val remoteWordInfos = api.getWordInfo(word)
            // replace the Items in the DB to what we get from the API
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch (e: HttpException){
            emit(Resource.Error(
                message = "Izah bro",
                data = wordInfos
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach the server, tafuta net bro",
                data = wordInfos
            ))
        }

        // If no errors
        val newWordInfos = dao.getWordInfos(word). map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}