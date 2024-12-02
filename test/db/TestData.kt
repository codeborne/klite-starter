package db

import todos.Todo
import users.Role.USER
import users.User

/** Immutable domain object samples for unit tests */
object TestData {
  val user = User("Test", "User", USER)
  val todo = Todo("Buy groceries")
}
