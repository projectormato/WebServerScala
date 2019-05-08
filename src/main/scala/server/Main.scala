package server

import java.net._

object Main extends App {
  val PORT = 8000
  val server = new ServerSocket(PORT)
  println("HTTP Server Start! Listening at " + PORT + "!")
  while (true) {
    val socket = server.accept
    val serverThread = new ServerThread(socket)
    val thread = new Thread(serverThread)
    thread.start()
  }

}