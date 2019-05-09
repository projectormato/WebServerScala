package server

import java.net._
import java.io._
import java.nio.file._
import scala.util.control.Breaks

class ServerThread(socket: Socket) extends Runnable {
  private val DOCUMENT_ROOT = "/home/keita/IdeaProjects/WebServerScala/src/HTML"
  private val ERROR_DOCUMENT = "/home/keita/IdeaProjects/WebServerScala/src/HTML"
  private val SERVER_NAME = "localhost:8000"
  private val mySocket = socket

  override def run(): Unit = {
    var output: OutputStream = null
    val b = new Breaks

    try {
      var input: InputStream = socket.getInputStream
      var path, ext, host = ""
      var line = Util.readLine(input)
      b.breakable(
        while (line != null) {
          if (line == "") {
            b.break
          }
          if (line.startsWith("GET")) {
            path = MyURLDecoder.decode(line.split(" ")(1), "UTF-8")
            var tmp = path.split("\\.")
            ext = tmp(tmp.length -1)
          } else if (line.startsWith("Host:")) {
            host = line.substring("Host: ".length())
          }

        }
      )

      if (path == ""){
        return
      }

      if (path.endsWith("/")) {
        path += "index.html"
        ext = "html"
      }

      output = new BufferedOutputStream(socket.getOutputStream)
      var fs = FileSystems.getDefault
      var pathObj = fs.getPath(DOCUMENT_ROOT + path)
      var realPath: Path = null
      try {
        realPath = pathObj.toRealPath()
        System.out.println(realPath)
      } catch {
        case ex: NoSuchFileException =>
          SendResponse.sendNotFoundResponse(output, ERROR_DOCUMENT)
      }

      if (!realPath.startsWith(DOCUMENT_ROOT)) {
        SendResponse.sendNotFoundResponse(output, ERROR_DOCUMENT)
      }
      else if (Files.isDirectory(realPath)) {
        val location = "http://" + (if (host != null) host
        else SERVER_NAME) + path + "/"
        SendResponse.sendMovePermanentlyResponse(output, location)
      }

      try {
        val fis = new BufferedInputStream(Files.newInputStream(realPath))
        SendResponse.sendOkResponse(output, fis, ext)
      }catch {
          case ex: FileNotFoundException =>
            SendResponse.sendNotFoundResponse(output, ERROR_DOCUMENT)
      }

    }

  }
}
