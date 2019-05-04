import java.net.ServerSocket

object SocketServer extends App {
  // ポート番号。定数なので大文字
  val PORT = 8000

  val server = new ServerSocket(PORT)
  println("HTTP Server Start! Listening at " + PORT + "!")
  val socket = server.accept
  println("Hello!")
  socket.close()

}