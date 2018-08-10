package com.haima.seal.worker

import java.io.File

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Worker extends Actor with ActorLogging {


  def receive = {
    case "test" ⇒ log.info("received test")
    case _ ⇒ log.info("received unknown message")
  }
}

object Worker extends App {

  //get the configuration file from classpath
  val configFile = getClass.getClassLoader.getResource("remote_application.conf").getFile
  //parse the config
  val config = ConfigFactory.parseFile(new File(configFile))
  val workerSystem = ActorSystem.create("workerSystem",config)
  val workerActorRef = workerSystem.actorOf(Props[Worker], "worker-actor")
  println(workerActorRef.path.toString)
//  val log = new Logging()
//  log.info(s"worker-actor")
//  workerActorRef ! "other"

    println(">>> remote worker is ready <<<")
  //  try StdIn.readLine()
  //  finally system.terminate()

}