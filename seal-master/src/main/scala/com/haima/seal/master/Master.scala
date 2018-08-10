package com.haima.seal.master

import akka.actor.{Actor, ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.haima.seal.master.supervisor.{SupervisorActor, SupervisorController}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
//import com.haima.seal.master.webserver.WebServer

/**
  * Methods to access the character-based console device, if any, associated
  * with the current Java virtual machine.
  *
  * <p> Whether a virtual machine has a console is dependent upon the
  * underlying platform and also upon the manner in which the virtual
  * machine is invoked.  If the virtual machine is started from an
  * interactive command line without redirecting the standard input and
  * output streams then its console will exist and will typically be
  * connected to the keyboard and display from which the virtual machine
  * was launched.  If the virtual machine is started automatically, for
  * example by a background job scheduler, then it will typically not
  * have a console.
  * <p>
  * If this virtual machine has a console then it is represented by a
  * unique instance of this class which can be obtained by invoking the
  * {@link java.lang.System#console()} method.  If no console device is
  * available then an invocation of that method will return <tt>null</tt>.
  * <p>
  * Read and write operations are synchronized to guarantee the atomic
  * completion of critical operations; therefore invoking methods
  * {@link #readLine()}, {@link #readPassword()}, {@link #format format()},
  * {@link #printf printf()} as well as the read, format and write operations
  * on the objects returned by {@link #reader()} and {@link #writer()} may
  * block in multithreaded scenarios.
  * <p>
  * Invoking <tt>close()</tt> on the objects returned by the {@link #reader()}
  * and the {@link #writer()} will not close the underlying stream of those
  * objects.
  * <p>
  * The console-read methods return <tt>null</tt> when the end of the
  * console input stream is reached, for example by typing control-D on
  * Unix or control-Z on Windows.  Subsequent read operations will succeed
  * if additional characters are later entered on the console's input
  * device.
  * <p>
  * Unless otherwise specified, passing a <tt>null</tt> argument to any method
  * in this class will cause a {@link NullPointerException} to be thrown.
  * <p>
  * <b>Security note:</b>
  * If an application needs to read a password or other secure data, it should
  * use {@link #readPassword()} or {@link #readPassword(String, Object...)} and
  * manually zero the returned character array after processing to minimize the
  * lifetime of sensitive data in memory.
  *
  * <blockquote><pre>
  * {@code
  * Console cons;
 * char[] passwd;
 * if ((cons = System.console()) != null &&
 *     (passwd = cons.readPassword("[%s]", "Password:")) != null) {
 *     ...
 *     java.util.Arrays.fill(passwd, ' ');
 * }
  * }
  * </pre></blockquote>
  *
  * @author Huawei Niu
  * @since 1.6
  */
class MasterActor extends Actor{
  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    /*
      Connect to remote actor. The following are the different parts of actor path
      akka.tcp : enabled-transports  of remote_application.conf
      RemoteSystem : name of the actor system used to create remote actor
      127.0.0.1:5150 : host and port
      user : The actor is user defined
      remote : name of the actor, passed as parameter to system.actorOf call
     */
    val remoteActor = context.actorSelection("akka.tcp://workerSystem@127.0.0.1:5150/user/worker-actor")
    println("That 's remote:" + remoteActor)
    remoteActor ! "hi"
  }
  override def receive: Receive = {

    case msg:String => {
      println("got message from remote" + msg)
    }
  }
}
object Master extends App with SupervisorController {


  implicit val actorSystem = ActorSystem(name = "MasterSystem")
  implicit val materializer = ActorMaterializer()
  // 这个在最后的 future flatMap/onComplete 里面会用到
  implicit val executionContext = actorSystem.dispatcher


  val supervisorActor: ActorRef = actorSystem.actorOf(SupervisorActor.props,"supervisorActor")

  lazy val apiRoutes: Route = pathPrefix("api") {
    supervisorRoutes
//    ~ sourceRoutes
  }

  Http().bindAndHandle(apiRoutes, "localhost", 8080)
  println(s"Server online at http://localhost:8080/")
  Await.result(actorSystem.whenTerminated, Duration.Inf)

}
