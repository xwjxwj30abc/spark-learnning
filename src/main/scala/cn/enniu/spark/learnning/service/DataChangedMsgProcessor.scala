package cn.enniu.spark.learnning.service

import cn.enniu.spark.learnning.utils.KafkaFactory
import org.apache.kafka.clients.producer.ProducerRecord
import org.json4s.JValue

/**
  * Created by xuwenjuan on 17/2/16.
  */
object DataChangedMsgProcessor extends MsgProcessor {

  val TOPIC_USER_DATA_SAVED = "topic_user_data_saved"

  val producer = KafkaFactory.createDefaultProducer()

  override def process(msg: String): Unit = {

  }

  override def doProcess(msg: JValue): Unit = {
    val module = 1
    val userId = 2
    val msg =s"""{"module":"${module}"}, {"userId": "${userId}"}"""
    val record = new ProducerRecord[String, String](TOPIC_USER_DATA_SAVED, null, msg)
    producer.send(record)
  }
}
