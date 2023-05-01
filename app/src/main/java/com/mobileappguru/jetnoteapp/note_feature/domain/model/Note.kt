package com.mobileappguru.jetnoteapp.note_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobileappguru.jetnoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)