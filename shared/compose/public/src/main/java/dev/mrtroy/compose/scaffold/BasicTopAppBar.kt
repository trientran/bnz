package dev.mrtroy.compose.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

/**
 * A composable function that creates a basic top app bar with the given parameters.
 *
 * @param modifier The modifier to be applied to the top app bar.
 * @param title A composable that defines the title text and style for the top app bar.
 * @param onUpClick A callback function to be called when the up navigation button is clicked.
 * @param scrollBehavior The behavior of the top app bar when it is scrolled.
 */
@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    onUpClick: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (onUpClick != null) {
                IconButton(
                    onClick = onUpClick,
                    modifier = Modifier.semantics { testTag = "appBarUpBtn" },
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
    )
}
