package dev.mrtroy.compose

import android.content.res.Resources
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.AndroidComposeUiTest
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.test.espresso.Espresso

/**
 * Runs an Android Compose UI test using the [androidx.compose.ui.test.runAndroidComposeUiTest] function.
 *
 * @param block a lambda that takes an [AndroidComposeUiTest] and performs the test actions
 * @receiver an inline function that suppresses the "NOTHING_TO_INLINE" warning
 * @throws Throwable if any exception is thrown during the test execution
 */
@Suppress("NOTHING_TO_INLINE")
@ExperimentalTestApi
inline fun runAndroidComposeUiTest(noinline block: AndroidComposeUiTest<ComponentActivity>.() -> Unit) =
    androidx.compose.ui.test.runAndroidComposeUiTest(block)

/**
 * Gets the [Resources] object associated with the [Activity] in this [AndroidComposeUiTest].
 *
 * @receiver an [AndroidComposeUiTest] instance whose activity has a [Resources] object
 * @return the [Resources] object associated with the activity in this test
 */
val <A : ComponentActivity> AndroidComposeUiTest<A>.resources: Resources get() = activity!!.resources

@Suppress("unused")
fun ComposeUiTest.pressBack() {
    // Use Espresso, because Compose doesn't yet provide an API for this
    Espresso.pressBack()
}
