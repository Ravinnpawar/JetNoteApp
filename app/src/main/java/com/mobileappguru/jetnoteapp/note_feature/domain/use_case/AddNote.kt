package com.mobileappguru.jetnoteapp.note_feature.domain.use_case

import com.mobileappguru.jetnoteapp.note_feature.domain.model.InvalidNoteException
import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.repository.NoteRepository

class AddNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note:Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}