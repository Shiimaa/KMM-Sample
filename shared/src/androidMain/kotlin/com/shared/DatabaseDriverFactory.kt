package com.shared

import android.content.Context
import com.kmmsampleapp.shared.db.AppDatabase
import com.shared.utils.ContextArgs
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "AppDatabase.db")
    }
}

actual fun getSqlDriver(contextArgs: ContextArgs?): SqlDriver {
    return AndroidSqliteDriver(AppDatabase.Schema, contextArgs?.mContext!!, "AppDatabase.db")
}
