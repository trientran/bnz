package dev.mrtroy.startup

data class Ordered<T>(val order: Int, val value: T) : Comparable<Ordered<T>> {
    override fun compareTo(other: Ordered<T>) = order.compareTo(other.order)
}
