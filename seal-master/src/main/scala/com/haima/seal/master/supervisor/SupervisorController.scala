package com.haima.seal.master.supervisor

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.haima.seal.master.supervisor.SupervisorActor._

import scala.concurrent.Future
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
  * @author: huawei niu
  * @version:
  * @createDate ：2018/8/6
  * @desc:
  * SupervisorController 是对采集器管理 web API 的响应处理，
  * 包括 启动、停止、维护、升级、卸载等web端命令的下发
  */
//object SupervisorController {
//
//}

trait SupervisorController extends SprayJsonSupport {

  def supervisorActor: ActorRef
  implicit val actorSystem: ActorSystem
  lazy val log = Logging(actorSystem, classOf[SupervisorController])

  implicit lazy val timeout = Timeout(5.seconds) // ask ? pattern implicit
  lazy val supervisorRoutes: Route = pathPrefix("supervisor") {
    get {
      val users: Future[Users] =
        (supervisorActor ? GetUsers).mapTo[Users]
      log.info(s"users are:{} ",users)
      complete("supervisor route return users")
    } ~
      pathEndOrSingleSlash {
        //        logger.info("pathEndOrSingleSlash debug here")
        complete("pathEndOrSingleSlash")
      }
  }
}


