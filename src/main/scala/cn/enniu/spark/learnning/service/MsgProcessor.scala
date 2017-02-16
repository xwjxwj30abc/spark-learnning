package cn.enniu.spark.learnning.service

import org.json4s.JValue

/**
  * Created by xuwenjuan on 17/2/16.
  */
trait MsgProcessor {

  def process(msg: String)

  def doProcess(msg: JValue)

}
