package com.mobileappguru.jetnoteapp.note_feature.domain.use_case

import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int):Note?{
        return repository.getNoteById(id)
    }
}