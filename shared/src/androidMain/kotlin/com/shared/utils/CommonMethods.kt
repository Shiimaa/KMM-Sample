package com.shared.utils

import android.content.Context
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class ContextArgs(
    var mContext: Context
)

actual fun initLogger() {
    Napier.base(DebugAntilog())
}

