import co.freeside.betamax.proxy.jetty.ProxyServer
import co.freeside.betamax.Recorder

class InvalidFunctionException extends Exception

object BetamaxHelper {

  /**
   * Execute the provided function wrapped in a proxy server injecting the
   * specified tape.
   *
   * @param tapeName Name of the tape to insert
   * @param functionUnderTest Function to execute
   */
  def withTape[T](tapeName:String, functionUnderTest: => T): T = {

    synchronized {

      val recorder = new Recorder
      val proxyServer = new ProxyServer(recorder)

      recorder.insertTape(tapeName)
      proxyServer.start()

      val response: T = try {
        functionUnderTest
      } finally {
        recorder.ejectTape()
        proxyServer.stop()
      }

      response
    }

  }

}