package com.haima.seal.worker

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

class Worker extends Actor with ActorLogging {


  def receive = {
    case "test" ⇒ log.info("received test")
    case _ ⇒ log.info("received unknown message")
  }
}

object Worker extends App {
  val workerSystem = ActorSystem.create("workerSystem")
  val workerActorRef = workerSystem.actorOf(Props[Worker], "worker-actor")
//  val log = new Logging()
//  log.info(s"worker-actor")
  workerActorRef ! "other"

  //  println(">>> Press ENTER to exit <<<")
  //  try StdIn.readLine()
  //  finally system.terminate()

}