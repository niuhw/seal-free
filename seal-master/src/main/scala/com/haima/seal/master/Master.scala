package com.haima.seal.master

import com.haima.seal.master.webserver.WebServer

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
object Master {
  def main(args: Array[String]): Unit = {
    new  WebServer().startServer("localhost", 8080)
  }
}