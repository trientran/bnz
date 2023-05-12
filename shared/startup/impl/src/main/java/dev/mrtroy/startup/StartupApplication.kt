package dev.mrtroy.startup

import android.app.Application
import javax.inject.Inject

/**

An abstract class for creating startup applications.

Subclasses should implement the [startupTaskRunner] property to specify the [StartupTaskRunner]

implementation to use and override the [onCreate] method to call the [startupTaskRunner]'s

[StartupTaskRunner.run] method during application startup.

The [StartupTaskRunner] is used to execute any startup tasks, such as initializing

libraries, databases, or other components, required by the application before it can run.

@see Application

@see StartupTaskRunner
 */
abstract class StartupApplication : Application() {

    /**

    The [StartupTaskRunner] implementation to use for executing startup tasks.
     */
    @Inject
    lateinit var startupTaskRunner: StartupTaskRunner

    /**

    Called when the application is starting.
    Subclasses should override this method to call the [startupTaskRunner]'s
    [StartupTaskRunner.run] method to execute any required startup tasks.
    @see StartupTaskRunner
     */
    override fun onCreate() {
        super.onCreate()
        startupTaskRunner.run()
    }
}
