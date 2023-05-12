package dev.mrtroy.compose

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag

/**
 * Returns a [SemanticsMatcher] for matching a Composable element with a test tag of "loadingScrim".
 *
 * @return a [SemanticsMatcher] for matching a Composable element with a test tag of "loadingScrim"
 */
fun isLoadingScrim(): SemanticsMatcher = hasTestTag("loadingScrim")

/**
 * Returns a [SemanticsMatcher] for matching a Composable element with a test tag of "appBarUpBtn".
 *
 * @return a [SemanticsMatcher] for matching a Composable element with a test tag of "appBarUpBtn"
 */
fun isAppBarUpBtn(): SemanticsMatcher = hasTestTag("appBarUpBtn")

/**
 * Asserts that a Snackbar with the given [text] is currently displayed.
 *
 * @param text the text to search for in the Snackbar
 */
fun ComposeUiTest.assertSnackBarDisplayed(text: String) {
    onNode(isSnackBar()).assertTextContains(text)
}

/**
 * Asserts that a Snackbar is not currently displayed.
 */
fun ComposeUiTest.assertSnackBarNotDisplayed() {
    onNode(isSnackBar()).assertDoesNotExist()
}

/**
 * Returns a [SemanticsMatcher] for matching a Composable element with a test tag of "snackBar".
 *
 * @return a [SemanticsMatcher] for matching a Composable element with a test tag of "snackBar"
 */
fun isSnackBar(): SemanticsMatcher = hasTestTag("snackBar")
