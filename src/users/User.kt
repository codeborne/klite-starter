package users

import db.Entity
import db.Id

enum class Role {
  ADMIN, USER
}

data class User(
  val firstName: String,
  val lastName: String,
  val role: Role = Role.ADMIN,
  override val id: Id<User> = Id()
): Entity<User>
