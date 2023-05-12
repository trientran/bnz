package dev.mrtroy.compose.sample

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

/**
 * Generates Lorem Ipsum text of specified length.
 *
 * @param words The number of words in the generated text.
 * @return A string containing the Lorem Ipsum text.
 */
fun loremIpsum(words: Int): String = LoremIpsum(words).values.first()
