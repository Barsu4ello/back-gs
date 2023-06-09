package com.dao.word

import com.models.ExposedWord
import com.models.Words
import com.plugins.DatabaseFactory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class WordRepositoryImpl : WordRepository {

    private fun resultRowToWord(row: ResultRow) = ExposedWord(
        id = row[Words.id],
        english = row[Words.english],
        russian = row[Words.russian],
    )

    override suspend fun findAllWords(): List<ExposedWord> = DatabaseFactory.dbQuery {
        Words.selectAll().map(::resultRowToWord)
    }

    override suspend fun findWordById(id: Long): ExposedWord? = DatabaseFactory.dbQuery {
        Words
            .select { Words.id eq id }
            .map(::resultRowToWord)
            .singleOrNull()
    }

    override suspend fun save(word: ExposedWord): Long = DatabaseFactory.dbQuery {
        Words.insert {
            it[english] = word.english
            it[russian] = word.russian
        }[Words.id]
    }

    override suspend fun update(id: Long, word: ExposedWord): Boolean =
        DatabaseFactory.dbQuery {
            Words.update({ Words.id eq id }) {
                it[english] = word.english
                it[russian] = word.russian
            } > 0
        }

    override suspend fun delete(id: Long): Boolean =
        DatabaseFactory.dbQuery {
            Words.deleteWhere { Words.id.eq(id) }
        } > 0
}