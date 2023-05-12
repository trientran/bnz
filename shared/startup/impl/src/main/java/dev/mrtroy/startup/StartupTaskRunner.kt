package dev.mrtroy.startup

import android.app.ActivityManager
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Process
import android.util.Log
import androidx.core.content.getSystemService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * A [StartupTaskRunner] is responsible for running all startup tasks in the correct order
 * at application startup. Tasks can be ordered or unordered, and can also be suspended tasks.
 * Additionally, it also registers activity lifecycle callbacks for tasks that require it.
 *
 * @param application The application instance
 * @param orderedTasks A [Provider] for a set of [Ordered] tasks to be executed in order
 * @param unorderedTasks A [Provider] for a set of unordered tasks to be executed
 * @param suspendTasks A [Provider] for a set of suspend tasks to be executed in parallel
 * @param activityLifecycleCallbacks A [Provider] for a set of activity lifecycle callbacks to be registered
 * @param globalScope The [CoroutineScope] to launch suspend tasks in
 */
class StartupTaskRunner @Inject constructor(
    private val application: Application,
    @Startup private val orderedTasks: Provider<Set<Ordered<() -> Unit>>>,
    @Startup private val unorderedTasks: Provider<Set<() -> Unit>>,
    @Startup private val suspendTasks: Provider<Set<suspend () -> Unit>>,
    private val activityLifecycleCallbacks: Provider<Set<ActivityLifecycleCallbacks>>,
    private val globalScope: CoroutineScope,
) {
    /**
     * Runs all the registered startup tasks in the correct order.
     */
    fun run() {
        if (!application.isMainProcess()) return
        Log.i("dev.mrtroy.startup.StartupTaskRunner", "Running startup tasks....")
        orderedTasks.get().sorted().forEach { it.value() }
        unorderedTasks.get().forEach { it() }
        globalScope.launch { suspendTasks.get().forEach { launch { it() } } }
        activityLifecycleCallbacks.get()
            .forEach { application.registerActivityLifecycleCallbacks(it) }
    }
}

/**
 * Checks whether the context is running in the main process or not.
 * This is used to prevent running startup tasks multiple times across multiple processes.
 *
 * @return `true` if the context is running in the main process, `false` otherwise
 */
private fun Context.isMainProcess(): Boolean {
    val currentPid = Process.myPid()
    val runningProcesses = getSystemService<ActivityManager>()!!.runningAppProcesses ?: return true
    return runningProcesses.none { it.pid == currentPid && it.processName != packageName }
}

/**
 * A Dagger module that provides default implementations of startup tasks.
 */
@Module
@InstallIn(SingletonComponent::class)
interface StartupTaskModule {
    /**
     * Provides a default set of ordered tasks.
     *
     * @return An empty set of [Ordered] tasks
     */
    @Multibinds
    @Startup
    fun defaultOrderedTasks(): Set<Ordered<() -> Unit>>

    /**
     * Provides a default set of unordered tasks.
     *
     * @return An empty set of unordered tasks
     */
    @Multibinds
    @Startup
    fun defaultUnorderedTasks(): Set<() -> Unit>

    /**
     * Provides a default set of suspend tasks.
     *
     * @return An empty set of suspend tasks
     */
    @Multibinds
    @Startup
    fun defaultSuspendTasks(): Set<suspend () -> Unit>

    /**
     * Provides a default set of activity lifecycle callbacks.
     *
     * @return An empty set of [ActivityLifecycleCallbacks]
     */
    @Multibinds
    fun defaultActivityLifecycleCallbacks(): Set<ActivityLifecycleCallbacks>
}
