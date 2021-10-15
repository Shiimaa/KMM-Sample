package com.shared.operations.db

//import com.kmmsampleapp.shared.db.AppDatabase
//import com.squareup.sqldelight.db.SqlDriver
//import comkmmsampleappshareddb.Test


//class Database(sqlDriver: SqlDriver) {
//    private val database = AppDatabase(sqlDriver)
//    private val dbQuery = database.appDatabaseQueries
//
//    fun clearDatabase() {
//        dbQuery.transaction {
//            dbQuery.removeAllUser()
//        }
//    }
//
//    fun getAllUser(): List<String> {
//        return dbQuery.selectAllUser().executeAsList()
//    }
//
//    fun insertUser(name: String) {
//        dbQuery.insertUser(
//            name = name
//        )
//    }
//}