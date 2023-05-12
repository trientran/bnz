package dev.mrtroy.compose.loadingscrim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview

/**
 * A composable function that displays a loading scrim when [isLoading] is true.
 * The scrim consists of a [CircularProgressIndicator] in the center of the screen.
 *
 * @param isLoading a boolean value indicating whether or not to show the loading scrim.
 * @param modifier optional [Modifier] to apply to the composable.
 */
@Composable
fun LoadingScrim(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = modifier.semantics(mergeDescendants = true) { testTag = "loadingScrim" })
        }
    }
}

@Preview
@Composable
private fun LoadingScrimPreview() = Box(Modifier.background(Color.White)) {
    LoadingScrim(isLoading = true)
}
