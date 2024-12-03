package db

import db.TestData.user
import klite.Config
import klite.isTest
import klite.jdbc.DBMigrator
import klite.jdbc.exec
import klite.jdbc.useAppDBUser
import org.junit.jupiter.api.BeforeEach
import users.UserRepository

abstract class DBTest: klite.jdbc.DBTest() {
  companion object {
    init {
      Config["DB_URL"] += "_test"
      DBMigrator().apply {
        if (!Config.isTest) error("Should be test context, but is " + Config.active)
        migrate()
      }
      useAppDBUser()
      UserRepository(db).save(user)
    }
  }

  @BeforeEach override fun startTx() {
    super.startTx()
    db.exec("call set_app_user(${user.id.value})") {}
  }
}
