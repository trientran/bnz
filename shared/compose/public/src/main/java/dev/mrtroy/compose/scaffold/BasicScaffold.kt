/*
 * Copyright (c)2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.mrtroy.compose.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

/**
 * A composable that creates a basic scaffold with a top app bar and content.
 *
 * @param title The content to be displayed in the top app bar.
 * @param modifier Optional modifier for this composable.
 * @param onUpClick Optional callback when the up arrow is clicked.
 * @param contentWindowInsets Optional [WindowInsets] object to be used for the content.
 * @param content The content to be displayed.
 */
@Composable
fun BasicScaffold(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onUpClick: (() -> Unit)? = null,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    BasicScaffold(
        topBar = {
            BasicTopAppBar(
                title = title,
                scrollBehavior = scrollBehavior,
                onUpClick = onUpClick,
            )
        },
        contentWindowInsets = contentWindowInsets,
        content = content,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    )
}

/**
 * A composable that creates a basic scaffold with a top app bar and content.
 *
 * @param topBar The content to be displayed in the top app bar.
 * @param modifier Optional modifier for this composable.
 * @param contentWindowInsets Optional [WindowInsets] object to be used for the content.
 * @param content The content to be displayed.
 */
@Composable
fun BasicScaffold(
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        contentWindowInsets = contentWindowInsets,
        content = content,
    )
}
