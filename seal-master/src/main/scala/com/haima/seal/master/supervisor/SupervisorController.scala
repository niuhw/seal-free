package com.haima.seal.master.supervisor

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
  * @author: huawei niu
  * @version:
  * @createDate ：2018/8/6
  * @desc:
  * SupervisorController 是对采集器管理 web API 的响应处理，
  * 包括 启动、停止、维护、升级、卸载等web端命令的下发
  */
object SupervisorController {

}

trait SupervisorController {
  //初始化相关变量
  implicit def actorSystem: ActorSystem

  lazy val logger = Logging(actorSystem, classOf[SupervisorController])

  lazy val supervisorRoutes: Route = pathPrefix("supervisor") {
    get {
      complete("supervisor is okay!")
    } ~
      pathEndOrSingleSlash {
        logger.info("pathEndOrSingleSlash debug here")
        complete("pathEndOrSingleSlash")
      }
  }
}


