package com.github.minimalistic.presentation.core.pm

interface ExceptionParser {

    fun parse(e: Exception): String
}