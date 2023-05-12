package dev.mrtroy.compose.button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.mrtroy.compose.sample.loremIpsum

/**
 * A composable function that displays a Material 3 button with the given [text] and [fontSize].
 * The button is clickable and will invoke the [onClick] lambda when clicked.
 * The [modifier] parameter can be used to modify the button's layout and appearance.
 *
 * @param text The text to display on the button.
 * @param onClick The lambda to invoke when the button is clicked.
 * @param modifier Optional [Modifier] used to modify the button's layout and appearance.
 * @param fontSize The font size to use for the button text.
 */
@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    enabled: Boolean = true,
) {
    androidx.compose.material3.Button(
        onClick = onClick, modifier = modifier,
        enabled = enabled,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = fontSize)
        )
    }
}

@Preview
@Composable
private fun Default() = Button(text = loremIpsum(3), onClick = {})
