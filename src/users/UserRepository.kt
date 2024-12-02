package users

import db.CrudRepository
import klite.jdbc.exec
import javax.sql.DataSource

class UserRepository(db: DataSource): CrudRepository<User>(db, "users") {
  fun setAppUser(user: User) { db.exec("call set_app_user(?)", sequenceOf(user.id)) }
}
