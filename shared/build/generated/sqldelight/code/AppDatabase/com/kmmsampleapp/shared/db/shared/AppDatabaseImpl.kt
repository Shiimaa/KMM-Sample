package com.kmmsampleapp.shared.db.shared

import com.kmmsampleapp.shared.db.AppDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import comkmmsampleappshareddb.AppDatabaseQueries
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE Test (
          |    name TEXT NOT NULL
          |    )
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val selectAllUser: MutableList<Query<*>> = copyOnWriteList()

  public override fun selectAllUser(): Query<String> = Query(-363752272, selectAllUser, driver,
      "AppDatabase.sq", "selectAllUser", """
  |SELECT *
  |FROM Test
  """.trimMargin()) { cursor ->
    cursor.getString(0)!!
  }

  public override fun insertUser(name: String): Unit {
    driver.execute(859513316, """
    |INSERT OR FAIL INTO Test(name)
    |VALUES(?)
    """.trimMargin(), 1) {
      bindString(1, name)
    }
    notifyQueries(859513316, {database.appDatabaseQueries.selectAllUser})
  }

  public override fun removeAllUser(): Unit {
    driver.execute(108283944, """DELETE FROM Test""", 0)
    notifyQueries(108283944, {database.appDatabaseQueries.selectAllUser})
  }
}
