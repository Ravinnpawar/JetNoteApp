package com.mobileappguru.jetnoteapp.note_feature.presentation.notes

import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.util.NoteOrder
import com.mobileappguru.jetnoteapp.note_feature.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
