package server

import java.util._
import java.io._

object MyURLDecoder {

  // 16進数2桁をASCIIコードで示すbyteを、intに変換する
  private def hex2int(b1: Byte, b2: Byte): Int = {
    var digit = 0
    if (b1 >= 'A') {
      digit = (b1 & 0xDF) - 'A' + 10
    } else {
      digit = b1 - '0'
    }
    digit *= 16
    if (b2 >= 'A') {
      digit += (b2 & 0xDF) - 'A' + 10
    } else {
      digit += b2 - '0'
    }
    digit
  }

  @throws[UnsupportedEncodingException]
  def decode(src: String, enc: String): String = {
    val srcBytes = src.getBytes("ISO_8859_1")
    val destBytes = new Array[Byte](srcBytes.length)
    var destIdx = 0
    var srcIdx = 0
    while (srcIdx < srcBytes.length) {
      if (srcBytes(srcIdx) == '%'.toByte) {
        destBytes(destIdx) = hex2int(srcBytes(srcIdx + 1), srcBytes(srcIdx + 2)).asInstanceOf[Byte]
        srcIdx += 2
      } else {
        destBytes(destIdx) = srcBytes(srcIdx)
      }
      destIdx += 1
    }
    val destBytes2 = java.util.Arrays.copyOf(destBytes, destIdx)
    new String(destBytes2, enc)
  }

}
