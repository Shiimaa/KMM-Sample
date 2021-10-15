package comkmmsampleappshareddb

import kotlin.String

public data class Test(
  public val name: String
) {
  public override fun toString(): String = """
  |Test [
  |  name: $name
  |]
  """.trimMargin()
}
