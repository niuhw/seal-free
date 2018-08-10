package com.haima.seal.master.supervisor

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.{HttpRequest, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.junit.runner.RunWith
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

/**
  * @author: huawei niu
  * @version:
  * @createDate ：2018/8/9
  * @desc:
  */
@RunWith(classOf[JUnitRunner])
class SupervisorSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest with SupervisorController {

  override implicit val actorSystem: ActorSystem = ActorSystem(name = "MasterSystem")

  override def supervisorActor: ActorRef = actorSystem.actorOf(SupervisorActor.props, "supervisorActor")

  lazy val routes = supervisorRoutes

  "SupervisorRoutes" should {
    "" in {
      val request = HttpRequest(uri = "/supervisor")
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
      }
    }
  }
}
