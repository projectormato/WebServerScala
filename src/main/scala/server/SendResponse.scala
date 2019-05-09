package server

import java.io._

object SendResponse {
  // 200のとき
  @throws[Exception]
  def sendOkResponse(output: OutputStream, fis: InputStream, ext: String): Unit = {
    Util.writeLine(output, "HTTP/1.1 200 OK")
    Util.writeLine(output, "Date: " + Util.getDateStringUtc)
    Util.writeLine(output, "Server: Modoki/0.2")
    Util.writeLine(output, "Connection: close")
    Util.writeLine(output, "Content-type: " + Util.getContentType(ext))
    Util.writeLine(output, "")
    var ch = fis.read
    while (ch != -1) {
      output.write(ch)
      ch = fis.read
    }
  }

  // 301のとき(リダイレクト)
  @throws[Exception]
  def sendMovePermanentlyResponse(output: OutputStream, location: String): Unit = {
    Util.writeLine(output, "HTTP/1.1 301 Moved Permanently")
    Util.writeLine(output, "Date: " + Util.getDateStringUtc)
    Util.writeLine(output, "Server: Modoki/0.2")
    Util.writeLine(output, "Location: " + location)
    Util.writeLine(output, "Connection: close")
    Util.writeLine(output, "")
  }

  // 404のとき
  @throws[Exception]
  def sendNotFoundResponse(output: OutputStream, errorDocumentRoot: String): Unit = {
    Util.writeLine(output, "HTTP/1.1 404 Not Found")
    Util.writeLine(output, "Date: " + Util.getDateStringUtc)
    Util.writeLine(output, "Server: Modoki/0.2")
    Util.writeLine(output, "Connection: close")
    Util.writeLine(output, "Content-type: text/html")
    Util.writeLine(output, "")
    val fis = new BufferedInputStream(new FileInputStream(errorDocumentRoot + "/404.html"))
    var ch = fis.read
    while (ch != -1) {
      output.write(ch)
      ch = fis.read
    }
  }

}
