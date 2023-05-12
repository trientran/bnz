/*Copyright 2013 Jake Wharton

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package dev.mrtroy.logging

import timber.log.Timber

/**
 * Utility class that wraps Timber logging library to simplify logging with different log levels.
 *
 * @param tag the tag to be used when logging
 */
class Logger(private val tag: String) {
    /**
     * Logs a verbose message.
     *
     * @param message the message to be logged
     */
    fun v(message: String?) {
        Timber.tag(tag)
        Timber.v(message)
    }

    /**
     * Logs a verbose message with an exception.
     *
     * @param e the exception to be logged
     * @param message the message to be logged
     */
    fun v(e: Throwable?, message: String?) {
        Timber.tag(tag)
        Timber.v(e, message)
    }

    /**
     * Logs a debug message.
     *
     * @param message the message to be logged
     */
    fun d(message: String?) {
        Timber.tag(tag)
        Timber.d(message)
    }

    /**
     * Logs a debug message with an exception.
     *
     * @param e the exception to be logged
     * @param message the message to be logged
     */
    fun d(e: Throwable?, message: String?) {
        Timber.tag(tag)
        Timber.d(e, message)
    }

    /**
     * Logs an info message.
     *
     * @param message the message to be logged
     */
    fun i(message: String?) {
        Timber.tag(tag)
        Timber.i(message)
    }

    /**
     * Logs an info message with an exception.
     *
     * @param e the exception to be logged
     * @param message the message to be logged
     */
    fun i(e: Throwable?, message: String?) {
        Timber.tag(tag)
        Timber.i(e, message)
    }

    /**
     * Logs a warning message.
     *
     * @param message the message to be logged
     */
    fun w(message: String?) {
        Timber.tag(tag)
        Timber.w(message)
    }

    /**
     * Logs a warning message with an exception.
     *
     * @param e the exception to be logged
     * @param message the message to be logged
     */
    fun w(e: Throwable?, message: String?) {
        Timber.tag(tag)
        Timber.w(e, message)
    }

    /**
     * Logs an error message.
     *
     * @param message the message to be logged
     */
    fun e(message: String?) {
        Timber.tag(tag)
        Timber.e(message)
    }

    /**
     * Logs an error message with an exception.
     *
     * @param e the exception to be logged
     * @param message the message to be logged
     */
    fun e(e: Throwable?, message: String?) {
        Timber.tag(tag)
        Timber.e(e, message)
    }
}

@Suppress("unused")
inline fun <reified T : Any> T.Logger(): Logger = Logger(T::class.java.name)
