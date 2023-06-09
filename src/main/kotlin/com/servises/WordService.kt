package com.servises

import com.dao.word.WordRepository
import com.dao.word.WordRepositoryImpl
import com.models.ExposedWord

class WordService {

    private val wordRepository: WordRepository = WordRepositoryImpl()

    suspend fun findAllWords(): List<ExposedWord> = wordRepository.findAllWords()
    suspend fun findWordById(id: Long): ExposedWord?  = wordRepository.findWordById(id)
    suspend fun save(user: ExposedWord): Long = wordRepository.save(user);
    suspend fun update(id: Long, user: ExposedWord):Boolean = wordRepository.update(id, user)
    suspend fun delete(id: Long):Boolean = wordRepository.delete(id)
}