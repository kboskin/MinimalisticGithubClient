package com.github.minimalistic.presentation.utils

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

fun bundle(vararg pairs: Pair<String, Any>): Bundle =
    Bundle().apply {
        pairs.forEach {
            when (val any = it.second) {
                is CharSequence -> putCharSequence(it.first, any)
                is String -> putString(it.first, any)
                is Int -> putInt(it.first, any)
                is Boolean -> putBoolean(it.first, any)
                is Float -> putFloat(it.first, any)
                is Parcelable -> putParcelable(it.first, any)
                is Serializable -> putSerializable(it.first, any)
                else -> error("Add your case")
            }
        }
    }
