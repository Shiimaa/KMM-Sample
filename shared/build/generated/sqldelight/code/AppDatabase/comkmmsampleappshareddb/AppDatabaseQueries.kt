package comkmmsampleappshareddb

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun selectAllUser(): Query<String>

  public fun insertUser(name: String): Unit

  public fun removeAllUser(): Unit
}
