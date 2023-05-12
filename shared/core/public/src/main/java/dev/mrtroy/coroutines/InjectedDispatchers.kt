package dev.mrtroy.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

object InjectedDispatchers {

    fun setDispatchers(
        default: CoroutineDispatcher,
        io: CoroutineDispatcher,
        main: MainCoroutineDispatcher,
    ) {
        InjectedDispatchers.default = default
        InjectedDispatchers.io = io
        InjectedDispatchers.main = main
    }

    private lateinit var default: CoroutineDispatcher
    private lateinit var io: CoroutineDispatcher
    private lateinit var main: MainCoroutineDispatcher

    /** @see kotlinx.coroutines.Dispatchers.Default */
    val Default: CoroutineDispatcher get() = default

    /** @see kotlinx.coroutines.Dispatchers.IO */
    val IO: CoroutineDispatcher get() = io

    /** @see kotlinx.coroutines.Dispatchers.Main */
    val Main: MainCoroutineDispatcher get() = main
}
