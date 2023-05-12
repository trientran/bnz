package dev.mrtroy.exceptions

val Throwable.isConnectionProblem
    get() = this is java.net.UnknownHostException ||
            this is java.net.SocketException ||
            this is java.net.SocketTimeoutException
