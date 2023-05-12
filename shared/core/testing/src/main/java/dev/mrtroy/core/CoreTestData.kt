package dev.mrtroy.core

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.util.UUID
import kotlin.random.Random

/**
 * An extension function that generates a random [String] of the specified [length].
 *
 * @param length the length of the generated [String]; defaults to 64
 * @return a random [String] of the specified [length]
 */
val random = Random(0)

fun Random.nextString(length: Int = 64): String {
    val bytes = ByteArray(length)
    nextBytes(bytes)
    return bytes.map { CHARS[(128 + it.toInt()) % CHARS.size] }.joinToString("")
}

/**
 * An extension function that generates a random [HttpUrl].
 *
 * @return a random [HttpUrl]
 */
fun Random.nextHttpUrl(): HttpUrl = "https://${nextID()}.example.com/".toHttpUrl()

/**
 * A helper function that generates a random [UUID].
 *
 * @return a random [UUID]
 */
private fun Random.nextUUID(): UUID = UUID(nextLong(), nextLong())

/**
 * An extension function that generates a random ID as a [String].
 *
 * @return a random ID as a [String]
 */
fun Random.nextID(): String = nextUUID().toString()

private val CHARS = ('a'..'z') + ('A'..'Z') + ('0'..'9')
