package dev.mrtroy.logging

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.mrtroy.startup.Ordered
import dev.mrtroy.startup.Startup
import java.lang.Thread.UncaughtExceptionHandler

/** Prints the the full stacktrace of uncaught exceptions in `logcat` (spanning multiple calls if necessary). */
internal class UncaughtExceptionLogger(private val defaultHandler: UncaughtExceptionHandler?) :
    UncaughtExceptionHandler {

    private val log = Logger()

    override fun uncaughtException(thread: Thread, t: Throwable) {
        log.e(t, "FATAL EXCEPTION")
        // Delegate to default handler
        defaultHandler?.uncaughtException(thread, t)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UncaughtExceptionLoggerModule {
    @Provides
    @IntoSet
    @Startup
    internal fun initializer(): Ordered<() -> Unit> = Ordered(2) {
        Thread.setDefaultUncaughtExceptionHandler(
            UncaughtExceptionLogger(Thread.getDefaultUncaughtExceptionHandler())
        )
    }
}
