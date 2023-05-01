package com.mobileappguru.jetnoteapp.note_feature.presentation.notes

import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
