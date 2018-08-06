//package com.haima.seal.master.webserver
//
///**
//  * author: mr.niuhuawei@gmail.com
//  * version:
//  * createDate ：2018/8/1
//  * desc: WebServer is a module used to handle http/https requests from web client;
//  * It makes Master powerful to do web action.
//  */
//
//import akka.Done
//import akka.actor.{ActorSystem, Props}
//import akka.http.scaladsl.model._
//import akka.http.scaladsl.server.{HttpApp, Route}
//import com.haima.seal.master.services.PFWorkerService
//
//import scala.util.Try
//
//// Server definition
//object WebServer extends HttpApp {
//  val mPFWorkerService = this.systemReference.get().actorOf(Props[PFWorkerService], "PFWorkerService");
//
//  override def routes: Route =
//    path("hello") {
//      get {
//        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
//      }
//    } ~
//      path("test") {
//        get {
//          mPFWorkerService ! "1"
//          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say test</h1>"))
//        }
//      }
//
//  private def cleanUpResources(): Unit = ???
//
//  override def postServerShutdown(attempt: Try[Done], system: ActorSystem): Unit = {
//    cleanUpResources()
//  }
//}
