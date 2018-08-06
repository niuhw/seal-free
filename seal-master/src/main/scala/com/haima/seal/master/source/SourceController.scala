package com.haima.seal.master.source

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash, pathPrefix}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteConcatenation._

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
  implicit def actorSystem: ActorSystem
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
