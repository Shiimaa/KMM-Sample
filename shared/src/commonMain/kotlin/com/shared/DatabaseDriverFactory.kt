package com.shared

import com.shared.operations.db.Database
import com.shared.utils.ContextArgs
import com.squareup.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getSqlDriver(contextArgs: ContextArgs? = null): SqlDriver

object DatabaseCreator {
    fun getDataBase(contextArgs: ContextArgs?): Database {
        val sqlDriver = getSqlDriver(contextArgs)
        return Database(sqlDriver)
    }
}
