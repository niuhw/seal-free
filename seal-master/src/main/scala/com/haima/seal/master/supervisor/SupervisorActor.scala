package com.haima.seal.master.supervisor

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Supervisor manage worker instance from  remote .
  */

final case class User(name: String, age: Int, countryOfResidence: String)
final case class Users(users: Seq[User])

object SupervisorActor {
  final case class ActionPerformed(description: String)
  final case object GetUsers
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class DeleteUser(name: String)

  def props: Props = Props[SupervisorActor]
}
class SupervisorActor extends Actor with ActorLogging {
  import SupervisorActor._

  var users = Set.empty[User]

  def receive: Receive = {
    case GetUsers =>
      sender() ! Users(users.toSeq)
    case CreateUser(user) =>
      users += user
      sender() ! ActionPerformed(s"User \${user.name} created.")
    case GetUser(name) =>
      sender() ! users.find(_.name == name)
    case DeleteUser(name) =>
      users.find(_.name == name) foreach { user => users -= user }
      sender() ! ActionPerformed(s"User \${name} deleted.")
  }
}
