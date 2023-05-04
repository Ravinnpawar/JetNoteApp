package com.mobileappguru.jetnoteapp

import androidx.compose.material.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobileappguru.jetnoteapp.note_feature.presentation.MainActivity
import com.mobileappguru.jetnoteapp.note_feature.presentation.notes.NotesScreen
import com.mobileappguru.jetnoteapp.note_feature.presentation.util.Screen
import com.mobileappguru.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testUI() {
        // Verify that the NotesScreen is displayed

        composeTestRule.setContent {
            JetNoteAppTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithText("Notes").assertExists()
    }
}