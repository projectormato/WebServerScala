package server

import java.net.Socket

import scala.sys.process.processInternal._

class ServerThread(socket: Socket) extends Runnable {
  private val DOCUMENT_ROOT = "/home/keita/IdeaProjects/WebServerScala/src/HTML"
  private val ERROR_DOCUMENT = "/home/keita/IdeaProjects/WebServerScala/src/HTML"
  private val SERVER_NAME = "localhost:8000"
  private val mySocket = socket

  override def run(): Unit = {
    val output: OutputStream = null

    try {
      val input: InputStream = socket.getInputStream

      val line, path, ext, host = null


    }

  }
}
