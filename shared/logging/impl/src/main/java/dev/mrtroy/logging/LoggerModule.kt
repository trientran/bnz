package dev.mrtroy.logging

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import dev.mrtroy.startup.Ordered
import dev.mrtroy.startup.Startup
import timber.log.Timber

/**
 * A Dagger module that provides a [Timber.Tree] for logging debug information during application development.
 * This module is installed in the [SingletonComponent].
 */
@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    /**
     * Provides an empty set of [Timber.Tree]s for use by the [initializer] function.
     */
    @Provides
    @ElementsIntoSet
    fun defaultTrees(): Set<Timber.Tree> = emptySet()

    /**
     * Provides a [Timber.Tree] initializer for use during application startup.
     * The [trees] parameter is a set of [Timber.Tree]s provided by Dagger.
     */
    @Provides
    @IntoSet
    @Startup
    fun initializer(trees: @JvmSuppressWildcards Set<Timber.Tree>): Ordered<() -> Unit> =
        Ordered(1) {
            Timber.plant(*trees.toTypedArray())
        }
}
