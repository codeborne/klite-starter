package todo

import db.CrudRepository
import db.Entity
import db.Id
import java.time.Instant
import javax.sql.DataSource

class TodoRepository(db: DataSource): CrudRepository<Todo>(db, "todos")

data class Todo(
  val item: String,
  val completedAt: Instant? = null,
  override val id: Id<Todo> = Id()
): Entity<Todo>
