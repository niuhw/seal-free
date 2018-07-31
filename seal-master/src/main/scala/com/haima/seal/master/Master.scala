package com.haima.seal.master

import com.haima.seal.worker.util.Util
import akka.actor.ActorSystem
import akka.http.scaladsl.{ConnectionContext, Http}
import akka.http.scaladsl.model.{ HttpRequest, HttpResponse, HttpEntity, ContentTypes}
import akka.stream.scaladsl.Flow
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{get}
import akka.http.scaladsl.server.directives.PathDirectives.{ path }
import akka.http.scaladsl.server.directives.RouteDirectives.{  complete}

object Master extends App {
   println(Util.shuchu())
   println("Hello, world") 
   implicit val context: ActorSystem = ActorSystem("master")
   final val web = Http(context)
   val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }
//    val routes: Flow[HttpRequest, HttpResponse, Any] = handle
   web.bindAndHandle(route, "localhost", 9999)
   println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
}