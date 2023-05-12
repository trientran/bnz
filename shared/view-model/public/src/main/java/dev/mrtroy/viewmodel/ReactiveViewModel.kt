package dev.mrtroy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mrtroy.logging.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * A base ViewModel class that provides reactive state management using a [MutableStateFlow]. The current state can be accessed through the public [state] property.
 * When a new state is emitted, it is logged using a [Logger] instance.
 *
 * @param State the type of the state object
 * @property initialState the initial state of the ViewModel
 */
abstract class ReactiveViewModel<State : Any>(initialState: State) : ViewModel() {

    private val log = Logger(this::class.java.name)

    // MutableStateFlow to hold the state of the ViewModel
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    // Public read-only property to access the current state
    val state: State get() = _state.value

    init {
        // Collect the state changes and log them using the provided logger instance
        viewModelScope.launch {
            _state.collect {
                log.v(it.toString())
            }
        }
    }

    /**
     * Returns a [StateFlow] object for the current state of the ViewModel.
     * This can be used by external classes to observe the state changes.
     */
    fun state(): StateFlow<State> = _state

    /**
     * A function to update the state of the ViewModel using a state reducer function.
     * The reducer function takes the current state as input and returns a new state object.
     *
     * @param reducer a function that takes the current state and returns a new state object
     */
    protected fun setState(reducer: State.() -> State) {
        _state.value = reducer(_state.value)
    }
}
