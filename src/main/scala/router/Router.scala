package router

import akka.http.scaladsl.server.{Directives, Route}
import repositories.TodoRepository




trait Router {
  def route: Route
}

class TodoRouter(todoRepository: TodoRepository) extends Router with Directives {
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  override def route: Route = pathPrefix("todos") {
    pathEndOrSingleSlash {
      get {
        complete(todoRepository.all)
      }
    }
  }
}