//package com.haima.seal.master.transformation
//
//import akka.actor.{Actor, ActorLogging, ActorSystem, Props, RootActorPath}
//import akka.cluster.{Cluster, Member, MemberStatus}
//import akka.cluster.ClusterEvent.{CurrentClusterState, MemberUp}
//import com.typesafe.config.ConfigFactory
//
///**
//  * @author: huawei niu
//  * @version:
//  * @createDate ：2018/8/6
//  * @desc:
//  */
//class TransformationBackend extends Actor with ActorLogging{
//
//  val cluster = Cluster(context.system)
//
//  // subscribe to cluster changes, MemberUp
//  // re-subscribe when restart
//  override def preStart(): Unit = cluster.subscribe(self, classOf[MemberUp])
//  override def postStop(): Unit = cluster.unsubscribe(self)
//
//  def receive = {
//    case TransformationJob(text) => sender() ! TransformationResult(text.toUpperCase)
//    case state: CurrentClusterState =>
//      state.members.filter(_.status == MemberStatus.Up) foreach register
//    case MemberUp(m) => register(m)
//  }
//
//  def register(member: Member): Unit =
//    if (member.hasRole("frontend"))
//      context.actorSelection(RootActorPath(member.address) / "user" / "frontend") !
//        BackendRegistration
//}
////#backend
//
//object TransformationBackend {
//  def main(args: Array[String]): Unit = {
//    // Override the configuration of the port when specified as program argument
//    val port = if (args.isEmpty) "0" else args(0)
//    val config = ConfigFactory.parseString(s"""
//        akka.remote.netty.tcp.port=$port
//        akka.remote.artery.canonical.port=$port
//        """)
//      .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
//      .withFallback(ConfigFactory.load())
//
//    val system = ActorSystem("ClusterSystem", config)
//    system.actorOf(Props[TransformationBackend], name = "backend")
//  }
//}
