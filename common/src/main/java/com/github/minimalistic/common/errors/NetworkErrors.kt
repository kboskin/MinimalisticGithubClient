package com.github.minimalistic.common.errors


sealed class NetworkErrors : Exception() {
    object ConnectionError : NetworkErrors()
    object ServerError : NetworkErrors()
    object NotFoundError : NetworkErrors()
}