package dev.mrtroy.mobile.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mrtroy.coroutines.Default
import dev.mrtroy.logging.Logger
import dev.mrtroy.schools.SchoolRepo
import dev.mrtroy.viewmodel.ReactiveViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val schoolRepo: SchoolRepo,
    @Default private val defaultDispatcher: CoroutineDispatcher,
) : ReactiveViewModel<HomeState>(HomeState()) {

    private val log = Logger()

    init {
        viewModelScope.launch { load() }
    }

    private suspend fun load() {
        setState { copy(isLoading = true, error = null) }
        try {
            schoolRepo
                .schools(20)
                .map {
                    withContext(defaultDispatcher) {
                        it.map { it.toSchool() }
                    }.toImmutableList()
                }
                .collectLatest { setState { copy(schools = it, isLoading = false) } }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            log.e(e, "Error fetching schools")
            setState { copy(error = HomeState.Error(e), isLoading = false) }
        }
    }
}
