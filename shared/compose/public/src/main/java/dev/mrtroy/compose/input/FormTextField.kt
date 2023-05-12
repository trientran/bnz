package dev.mrtroy.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mrtroy.compose.sample.loremIpsum

/**
 * A Composable function that renders a simple form text field with a gray background and rounded corners.
 *
 * @param value the current value of the text field.
 * @param onValueChange a callback function that is called when the text field's value changes.
 * @param modifier optional Modifier that can be used to modify the text field's layout properties.
 * @param keyboardOptions optional keyboard options to apply to the text field.
 * @param keyboardActions optional keyboard actions to apply to the text field.
 * @param singleLine a boolean indicating whether the text field should be single-line or multi-line.
 */
@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean,
) {
    BasicTextField(
        value = value,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(6.dp))
            .padding(8.dp),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onValueChange = onValueChange,
    )
}

@Preview
@Composable
private fun Default() {
    FormTextField(
        value = loremIpsum(4),
        onValueChange = {},
        modifier = Modifier.height(160.dp),
        singleLine = false,
    )
}
