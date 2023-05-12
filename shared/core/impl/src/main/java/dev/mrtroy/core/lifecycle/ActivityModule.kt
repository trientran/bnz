package dev.mrtroy.core.lifecycle

import android.app.Application.ActivityLifecycleCallbacks
import androidx.activity.ComponentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * A Dagger Hilt module providing an [ActivityLifecycleCallbacks] instance for tracking activity lifecycle events,
 * as well as a [Flow] of the current top [ComponentActivity].
 */
@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    /**
     * Provides an [ActivityLifecycleCallbacks] instance for tracking activity lifecycle events.
     *
     * @param tracker the [ActivityTracker] instance to use for tracking activity lifecycle events
     * @return an [ActivityLifecycleCallbacks] instance for tracking activity lifecycle events
     */
    @Provides
    @IntoSet
    fun initializer(tracker: ActivityTracker): ActivityLifecycleCallbacks = tracker

    /**
     * Provides a [Flow] of the current top [ComponentActivity].
     *
     * @param tracker the [ActivityTracker] instance to use for tracking activity lifecycle events
     * @return a [Flow] of the current top [ComponentActivity]
     */
    @Provides
    @Singleton
    fun topActivity(tracker: ActivityTracker): Flow<ComponentActivity?> = tracker.topActivity()
}
