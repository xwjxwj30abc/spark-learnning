package cn.enniu.spark.learnning.utils

import java.util
import java.util.Properties

import cn.enniu.spark.learnning.Settings
import org.apache.spark.streaming.{StreamingContext}
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

/**
  * Created by xuwenjuan on 17/2/16.
  */
object KafkaFactory {

  def createDefaultProducer(): KafkaProducer[String, String] = {
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Settings.get("kafka.servers"))
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }

  def createDefaultConsumer(): KafkaConsumer[String, String] = {
    val props = new Properties()
    props.put("bootstrap.servers", Settings.get("kafka.servers"))
    props.put("group.id", "spark_scala_group")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    new KafkaConsumer(props)
  }

  def createDefaultStreaming(ssc: StreamingContext, topic: String): InputDStream[ConsumerRecord[String, String]] = {
    val props = Map[String, Object](
      "bootstrap.servers" -> Settings.get("kafka.servers"),
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array(topic)
    KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, props))
  }
}
