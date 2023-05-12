package dev.mrtroy.core.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A class for tracking the lifecycle of [Activity] instances in the app.
 *
 * @constructor Creates an instance of [ActivityTracker].
 */
@Singleton
class ActivityTracker @Inject constructor() : Application.ActivityLifecycleCallbacks {

    private val startedActivities = MutableStateFlow<List<ComponentActivity>>(emptyList())
    private val topActivity = startedActivities.map { it.lastOrNull() }

    /**
     * Returns a [Flow] of the current top [ComponentActivity].
     *
     * @return a [Flow] of the current top [ComponentActivity]
     */
    fun topActivity(): Flow<ComponentActivity?> = topActivity

    override fun onActivityStarted(activity: Activity) {
        if (activity is ComponentActivity) startedActivities.update { it + activity }
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity is ComponentActivity) startedActivities.update { it - activity }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
    override fun onActivityResumed(activity: Activity) = Unit
    override fun onActivityPaused(activity: Activity) = Unit
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    override fun onActivityDestroyed(activity: Activity) = Unit
}
