package com.mobileappguru.jetnoteapp.note_feature.data.repository

import com.mobileappguru.jetnoteapp.note_feature.data.data_source.NoteDao
import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao :NoteDao): NoteRepository {


    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}