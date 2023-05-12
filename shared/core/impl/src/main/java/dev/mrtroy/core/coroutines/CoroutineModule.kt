package dev.mrtroy.core.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.mrtroy.coroutines.Default
import dev.mrtroy.coroutines.IO
import dev.mrtroy.coroutines.InjectedDispatchers
import dev.mrtroy.startup.Ordered
import dev.mrtroy.startup.Startup
import kotlinx.coroutines.*
import javax.inject.Singleton

/**
 * A Dagger Hilt module providing [CoroutineDispatcher] and [CoroutineScope] instances for use in the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    /**
     * Provides an instance of [CoroutineDispatcher] for the IO dispatcher.
     *
     * @return an instance of [CoroutineDispatcher] for the IO dispatcher
     */
    @Provides
    @IO
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides an instance of [CoroutineDispatcher] for the default dispatcher.
     *
     * @return an instance of [CoroutineDispatcher] for the default dispatcher
     */
    @Provides
    @Default
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    /**
     * Provides a singleton instance of [CoroutineScope] using a [SupervisorJob] and the default dispatcher.
     *
     * @param defaultDispatcher the default [CoroutineDispatcher] instance to use
     * @return a singleton instance of [CoroutineScope]
     */
    @Provides
    @Singleton
    fun globalScope(@Default defaultDispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(SupervisorJob() + defaultDispatcher)

    /**
     * Provides a [CoroutineScope] instance with the IO dispatcher as its [CoroutineContext].
     *
     * @param globalScope the global [CoroutineScope] instance to use as the parent scope
     * @param ioDispatcher the IO [CoroutineDispatcher] instance to use as the IO dispatcher
     * @return a [CoroutineScope] instance with the IO dispatcher as its [CoroutineContext]
     */
    @Provides
    @IO
    fun globalIOScope(
        globalScope: CoroutineScope,
        @IO ioDispatcher: CoroutineDispatcher
    ): CoroutineScope =
        globalScope + ioDispatcher

    /**
     * Provides an ordered set containing a single initializer function that sets the default, IO, and main
     * [CoroutineDispatcher] instances to be used by the app.
     *
     * @return an ordered set containing a single initializer function
     */
    @Provides
    @IntoSet
    @Startup
    fun initializer(): Ordered<() -> Unit> = Ordered(0) {
        InjectedDispatchers.setDispatchers(
            default = Dispatchers.Default,
            io = Dispatchers.IO,
            main = Dispatchers.Main,
        )
    }
}
