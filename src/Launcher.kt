import klite.Config
import klite.Server
import klite.annotations.annotated
import klite.isProd
import klite.json.JsonBody
import todo.TodoRoutes

fun main() {
  if (!Config.isProd) Config.useEnvFile()
  Server().apply {
    context("/api") {
      useOnly<JsonBody>()
      annotated<TodoRoutes>("/todos")
    }
    start()
  }
}
