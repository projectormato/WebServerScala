package server

import java.io._
class Util {


  @throws[Exception]
  def readLine(input: InputStream): String = {
    var ch = 0
    var ret = ""
    while ( {
      (ch = input.read) != -1
    }) if (ch == '\r') {
    }
    else if (ch == '\n') break //todo: break is not supported
    else ret += ch.toChar
    if (ch == -1) null
    else ret
  }
}
