package dev.mrtroy.coroutines

import javax.inject.Qualifier

/** Denotes a binding relates to I/O. */
@Qualifier
annotation class IO

/** Denotes a binding relates to the default coroutine dispatcher. */
@Qualifier
annotation class Default
