package dev.mrtroy.compose.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

/**
 * A base Fragment class for using Jetpack Compose in Android. It sets up a [ComposeView]
 * and provides a convenience method [setContent] to set the content of the view.
 * Extend this class and implement the content of the view in the [setContent] function.
 * */
abstract class ComposeFragment : Fragment() {

    /**
     * Sets up the [ComposeView] and its [ViewCompositionStrategy].
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    /**
     * Sets the content of the [ComposeView] with the provided [content] composable.
     * Wraps the composable in a [MaterialTheme] composable to provide Material styling.
     * */
    fun setContent(content: @Composable () -> Unit) {
        (view as ComposeView).setContent {
            MaterialTheme { content() }
        }
    }
}
