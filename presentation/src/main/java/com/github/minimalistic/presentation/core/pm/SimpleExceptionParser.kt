package com.github.minimalistic.presentation.core.pm

import javax.inject.Inject

class SimpleExceptionParser @Inject constructor() : ExceptionParser {
    override fun parse(e: Exception): String = e.localizedMessage
}