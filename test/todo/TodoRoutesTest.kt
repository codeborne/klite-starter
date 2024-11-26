package todo

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.TestData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

/** Route classes are plain Kotlin, very easy to unit-test */
class TodoRoutesTest {
  val repository = mockk<TodoRepository>(relaxed = true)
  val routes = TodoRoutes(repository)

  @Test fun todos() {
    every { repository.list() } returns listOf(TestData.todo)
    val todos = routes.all()
    expect(todos).toEqual(listOf(TestData.todo))
  }
}
