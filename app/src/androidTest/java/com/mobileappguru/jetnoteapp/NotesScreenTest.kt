package com.mobileappguru.jetnoteapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.mobileappguru.jetnoteapp.note_feature.presentation.notes.NotesScreen
import org.junit.Rule
import org.junit.Test

class NotesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun notesScreen_uiComponentsDisplayed() {
        // Launch the NotesScreen composable
        composeTestRule.setContent {
            val mockNavController = rememberNavController()
            NotesScreen(navController = mockNavController)
        }

        // Check that the UI components are displayed
        composeTestRule.onNodeWithText("Your note").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Sort").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Add note").assertIsDisplayed()
    }

    @Test
    fun notesScreen_orderSectionDisplayedOnClick() {
        // Launch the NotesScreen composable

        composeTestRule.setContent {
            val mockNavController = rememberNavController()
            NotesScreen(navController = mockNavController)
        }

        // Check that the order section is hidden initially
        composeTestRule.onNodeWithText("Title").assertDoesNotExist()

        // Click the sort button
        composeTestRule.onNodeWithContentDescription("Sort").performClick()

        // Check that the order section is displayed
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
    }

    @Test
    fun notesScreen_noteItemClicked_navigatesToAddEditNoteScreen() {
        // Launch the NotesScreen composable
        composeTestRule.setContent {
            val mockNavController = rememberNavController()
            NotesScreen(navController = mockNavController)
        }

        // Click on a note item
        //composeTestRule.onAllNodesWithContentDescription("Note item").random().performClick()

        // Check that the navigation action is triggered
        //verify(mockNavController).navigate(Screen.AddEditNoteScreen.route + "?noteId=1&noteColor=Red")
    }

    @Test
    fun notesScreen_deleteNoteItem_showsSnackbar() {
        // Launch the NotesScreen composable
        composeTestRule.setContent {
            //NotesScreen(navController = mockNavController)
        }

        // Click on the delete button of a note item
        //composeTestRule.onAllNodesWithContentDescription("Delete").random().performClick()

        // Check that the snackbar is displayed
        composeTestRule.onNodeWithText("Note deleted").assertIsDisplayed()
        composeTestRule.onNodeWithText("Undo").assertIsDisplayed()

        // Click the undo button
        composeTestRule.onNodeWithText("Undo").performClick()

        // Check that the restore note event is triggered
        //verify(mockNotesViewModel).onEvent(NotesEvent.RestoreNote)
    }
}