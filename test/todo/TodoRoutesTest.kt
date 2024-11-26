package todo

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import data.TestData
import org.junit.jupiter.api.Test

/** Route classes are plain Kotlin, very easy to unit-test */
class TodoRoutesTest {
  val routes = TodoRoutes()

  @Test fun todos() {
    val todos = routes.todos()
    expect(todos).toEqual(listOf(TestData.todo))
  }
}

