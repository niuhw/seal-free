package com.haima.seal.master.source

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash, pathPrefix}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteConcatenation._
import akka.stream.Materializer
import com.typesafe.config.Config

import scala.concurrent.ExecutionContextExecutor

/**
  * @author: niuhw@raysdata.com
  * @version:
  * @createDate ：2018/8/6
  * @desc:
  */

object SourceController {

}
trait SourceController {
  //初始化相关变量
//  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter
//TODO fix this bug
//  lazy val logger = Logging(actorSystem, classOf[SourceController])

  lazy val sourceRoutes: Route = pathPrefix("source") {
    get {
      complete("source is okay!")
    } ~
      pathEndOrSingleSlash {
//        logger.info("pathEndOrSingleSlash debug here")
        complete("sourceRoutes")
      }
  }
}
