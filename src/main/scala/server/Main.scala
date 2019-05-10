package server

import java.net._

object Main extends App {
  val PORT = 8000
  val server = new ServerSocket(PORT)
  println("HTTP Server Start! Listening at " + PORT + "!")
  while (true) {
    println("きた")
    val socket = server.accept
    new Thread(new ServerThread(socket)).start()
//    thread.start()
  }

}