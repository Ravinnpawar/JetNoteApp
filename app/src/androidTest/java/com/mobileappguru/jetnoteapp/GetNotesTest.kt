package com.mobileappguru.jetnoteapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.mobileappguru.jetnoteapp.note_feature.domain.model.Note
import com.mobileappguru.jetnoteapp.note_feature.domain.repository.NoteRepository
import com.mobileappguru.jetnoteapp.note_feature.domain.use_case.GetNotes
import com.mobileappguru.jetnoteapp.note_feature.domain.util.NoteOrder
import com.mobileappguru.jetnoteapp.note_feature.domain.util.OrderType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetNotesTest {

    private lateinit var repository: NoteRepository
    private lateinit var getNotes: GetNotes

    private val note1 = Note(
        id = 1,
        title = "Test Note 1",
        content = "Test content 1",
        color = Color.Cyan.toArgb(),
        timestamp = System.currentTimeMillis()
    )

    private val note2 = Note(
        id = 2,
        title = "Test Note 2",
        content = "Test content 2",
        color = Color.Magenta.toArgb(),
        timestamp = System.currentTimeMillis() - 10000
    )

    private val note3 = Note(
        id = 3,
        title = "Test Note 3",
        content = "Test content 3",
        color = Color.Yellow.toArgb(),
        timestamp = System.currentTimeMillis() + 10000
    )

    private val unsortedNotes = listOf(note1, note2, note3)

    @Before
    fun setup() {
        val notes = MutableStateFlow(unsortedNotes)
        repository = object : NoteRepository {
            override fun getNotes(): Flow<List<Note>> {
                return notes
            }

            override suspend fun getNoteById(id: Int): Note? {
                return null
            }
            override suspend fun insertNote(note: Note) {
                notes.value = notes.value + note
            }

            suspend fun update(note: Note) {
                notes.value = notes.value.map {
                    if (it.id == note.id) {
                        note
                    } else {
                        it
                    }
                }
            }

            override suspend fun deleteNote(note: Note) {
                notes.value = notes.value - note
            }

        }
        getNotes = GetNotes(repository)
    }

    @Test
    fun getnotesinascendingorderbytitle() = runTest {
        val result = getNotes.invoke(NoteOrder.Title(OrderType.Ascending)).first()
        assertEquals(listOf(note3, note1, note2), result)
    }

    @Test
    fun getnotesindescendingorderbytitle() = runTest {
        val result = getNotes.invoke(NoteOrder.Title(OrderType.Descending)).first()
        assertEquals(listOf(note2, note1, note3), result)
    }

    @Test
    fun getnotesinascendingorderbydate() = runTest {
        val result = getNotes.invoke(NoteOrder.Date(OrderType.Ascending)).first()
        assertEquals(listOf(note2, note1, note3), result)
    }

    @Test
    fun getnotesindescendingorderbydate() = runTest {
        val result = getNotes.invoke(NoteOrder.Date(OrderType.Descending)).first()
        assertEquals(listOf(note3, note1, note2), result)
    }

    @Test
    fun getnotesinascendingorderbycolor() = runTest {
        val result = getNotes.invoke(NoteOrder.Color(OrderType.Ascending)).first()
        assertEquals(listOf(note3, note1, note2), result)
    }

    @Test
    fun getnotesindescendingorderbycolor() = runTest {
        val result = getNotes.invoke(NoteOrder.Color(OrderType.Descending)).first()
        assertEquals(listOf(note2, note1, note3), result)
    }
}