package server

import java.io._
import java.util._
import java.text._

import scala.collection.immutable.HashMap
import scala.util.control.Breaks

object Util {
  // InputStreamからのバイト列を、行単位で読み込むユーティリティメソッド
  @throws[Exception]
  def readLine(input: InputStream): String = {
    val b = new Breaks
    var ch = 0
    var ret = ""
    ch = input.read
    b.breakable {
      while (true) {
        if (ch == -1) {
          b.break
        }
        if (ch == '\r') {
        } else if (ch == '\n') {
          b.break
        } else {
          ret += ch.asInstanceOf[Char]
        }
        ch = input.read
      }
    }
    if (ch == -1) {
      null
    } else {
      ret
    }
  }

  // 1列の文字列を、バイト列としてOutputStreamに書き込むユーティリティメソッド
  @throws[Exception]
  def writeLine(output: OutputStream, str: String): Unit = {
    for (ch <- str.toCharArray) {
      output.write(ch.toInt)
    }
    output.write('\r'.toInt)
    output.write('\n'.toInt)
  }

  // 現在時刻からHTTP標準に合わせてフォーマットされた日付文字列を返す
  def getDateStringUtc: String = {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    val df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US)
    df.setTimeZone(cal.getTimeZone)
    df.format(cal.getTime) + " GMT"
  }


  // 拡張子とContent-Typeの対応表// 拡張子とContent-Typeの対応表
  val contentTypeMap: HashMap[String, String] = HashMap(
    "html"-> "text/html",
    "htm" -> "text/html",
    "txt" -> "text/plain",
    "css" -> "text/css",
    "png" -> "image/png",
    "jpg" -> "image/jpeg",
    "jpeg" -> "image/jpeg",
    "gif" -> "image/gif",
  )

  // 拡張子を受け取りContent-Typeを返す
  def getContentType(ext: String): String = {
    val ret: Option[String] = contentTypeMap.get(ext.toLowerCase)
    if (ret.isEmpty){
      "application/octet-stream"
    }else{
      ret.get
    }
  }

}
