package dev.mrtroy.logging

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import timber.log.Timber

/**
 * Dagger Hilt module responsible for providing the Timber debug tree.
 * This module is installed in the SingletonComponent, meaning that the debug tree will be provided for
 * the entire application.
 * The tree provided is the Timber DebugTree, which logs messages to the console.
 */
@Module
@InstallIn(SingletonComponent::class)
object DebugTreeModule {
    @Provides
    @IntoSet
    fun tree(): Timber.Tree = Timber.DebugTree()
}
