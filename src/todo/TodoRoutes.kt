package todo

import klite.annotations.GET

class TodoRoutes(private val repository: TodoRepository) {
  @GET("/todos") fun all() = repository.list()
}
