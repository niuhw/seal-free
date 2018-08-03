import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import akka.testkit.TestKit
import org.scalatest.{FunSpec, MustMatchers, TestSuite}

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * @author: niuhw@raysdata.com
  * @version:
  * @createDate ：2018/8/3
  * @desc:
  */
//@RunWith(classOf[JUnitRunner])
class StreamsSpec(_system: ActorSystem) extends TestKit(_system) with MustMatchers with TestSuite {
  override def withFixture(test:NoArgTest): Unit =  {

  }
}
