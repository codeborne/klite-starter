package todos

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import db.DBTest
import db.TestData.todo
import org.junit.jupiter.api.Test

class TodoRepositoryTest: DBTest() {
  val repository = TodoRepository(db)

  @Test fun `save & load`() {
    repository.save(todo)
    expect(repository.get(todo.id)).toEqual(todo)
    expect(repository.list()).toContain(todo)
  }
}
