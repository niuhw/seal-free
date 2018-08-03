package com.haima.seal.master.services

import akka.actor.Actor

/**
  * @author: Huawei Niu
  * @version:
  * @createDate ：2018/8/3
  * @desc:
  */
case class AkkaMessage(message: Any)
case class Response(response: Any)

class PFWorkerService extends  Actor{
  override def receive : Receive = {
    //接收到的消息类型为AkkaMessage，则在前面加上response_，返回给sender
    case msg: AkkaMessage => {
      println("服务端收到消息: " + msg.message)
      sender ! Response("response_" + msg.message)
    }
    case _ => println("服务端不支持的消息类型 .. ")
  }
}
