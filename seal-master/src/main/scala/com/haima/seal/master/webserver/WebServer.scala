package com.haima.seal.master.webserver

/**
  * author: mr.niuhuawei@gmail.com
  * version:
  * createDate ：2018/8/1
  * desc: WebServer is a module used to handle http/https requests from web client;
  * It makes Master powerful to do web action.
  */

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{HttpApp, Route}

import scala.util.Try

// Server definition
class WebServer extends HttpApp {
  override def routes: Route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }

  private def cleanUpResources(): Unit = ???

  override def postServerShutdown(attempt: Try[Done], system: ActorSystem): Unit = {
    cleanUpResources()
  }
}
