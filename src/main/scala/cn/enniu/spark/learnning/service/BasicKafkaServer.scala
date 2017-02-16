package cn.enniu.spark.learnning.service

import org.apache.spark.SparkContext
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.slf4j.LoggerFactory

/**
  * Created by xuwenjuan on 17/2/16.
  */
trait BasicKafkaServer extends Serializable {

  val logger = LoggerFactory.getLogger(this.getClass)

  def getBatchduration: Long

  def getUpstreamTopic: String

  def getMsgProcessor: MsgProcessor

  def startUp(): Unit = {

    import cn.enniu.spark.learnning.utils._
    val conf = SparkUtils.getDefaultConf(this.getClass.getName)
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(getBatchduration))
    val stream = KafkaFactory.createDefaultStreaming(ssc, getUpstreamTopic)

    stream.foreachRDD(rdd => {
      if (rdd.count() == 0) {
        logger.debug("no message coming")
      } else {
        logger.info(s"get message count:${rdd.count()}")
        rdd.foreach(record => {
          val msg = record.value()
          getMsgProcessor.process(msg)
        })

        ssc.start()
        ssc.awaitTermination()
      }

    })
  }
}
