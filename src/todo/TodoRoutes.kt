package todo

import klite.annotations.GET
import java.time.Instant

class TodoRoutes {
  @GET("/todos") fun todos() = listOf(Todo("Buy groceries"))
}

data class Todo(val item: String, val completedAt: Instant? = null)
