//package com.shared
//
//import com.shared.operations.db.Database
//import com.shared.utils.ContextArgs
//import com.squareup.sqldelight.db.SqlDriver
//
//
//expect class DatabaseDriverFactory {
//    fun createDriver(): SqlDriver
//}
//
//expect fun getSqlDriver(contextArgs: ContextArgs? = null): SqlDriver
//
//object DatabaseCreator {
//    var sqlDriver: SqlDriver? = null
//    var database: Database? = null
//    fun getDataBase(contextArgs: ContextArgs?): Database {
//        if (sqlDriver == null)
//            sqlDriver = getSqlDriver(contextArgs)
//        if (database == null)
//            database = Database(sqlDriver!!)
//
//        return database!!
//    }
//}
