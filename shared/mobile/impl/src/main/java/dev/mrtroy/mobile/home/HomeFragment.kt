package dev.mrtroy.mobile.home

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.mrtroy.compose.fragment.ComposeFragment

@AndroidEntryPoint
class HomeFragment : ComposeFragment() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            val state by viewModel.state().collectAsStateWithLifecycle()
            HomeScreen(
                schools = state.schools,
                error = state.error,
                isLoading = state.isLoading,
            )
        }
    }
}
