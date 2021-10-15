package com.shared.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class ContextArgs(
)

actual fun initLogger() {
    Napier.base(DebugAntilog())
}
