package com.dao.word

import com.models.ExposedWord

interface WordRepository {
    suspend fun findAllWords(): List<ExposedWord>
    suspend fun findWordById(id: Long): ExposedWord?
    suspend fun save(word: ExposedWord): Long
    suspend fun update(id: Long, word: ExposedWord):Boolean
    suspend fun delete(id: Long): Boolean
}