package dev.mrtroy.compose.snackbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import dev.mrtroy.exceptions.isConnectionProblem
import dev.mrtroy.strings.R as strings_R

/**
 * Displays a Snackbar composable with the given message and dismiss action
 *
 * @param onErrorDismiss a function to be called when the dismiss button is clicked
 * @param messageRes the string resource ID to be used as the message in the Snackbar
 */
@Composable
fun SnackBar(
    onErrorDismiss: () -> Unit,
    @StringRes messageRes: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .semantics(mergeDescendants = true) { testTag = "snackBar" },
            content = { Text(text = stringResource(id = messageRes)) },
            action = {
                TextButton(onClick = onErrorDismiss) {
                    Text(text = stringResource(id = strings_R.string.dismiss))
                }
            }
        )
    }
}

@Composable
fun genericErrorMessage(exception: Throwable): String =
    stringResource(exception.genericMessageResource)

@get:StringRes
val Throwable.genericMessageResource: Int
    get() = if (isConnectionProblem) {
        strings_R.string.connectivity_error_message
    } else {
        strings_R.string.a_technical_issue_please_try_again
    }
