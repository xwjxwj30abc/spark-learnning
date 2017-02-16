package cn.enniu.spark.learnning

import cn.enniu.spark.learnning.dao.MongoDao
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

/**
  * Created by xuwenjuan on 17/2/16.
  */
object Settings {

  val logger = LoggerFactory.getLogger(Settings.getClass)
  val config = ConfigFactory.load("config")

  val env = get("env")

  def get(key: String): String = {
    config.getString(key)
  }

  def getConfig(path: String): Config = {
    config.getConfig(path)
  }

  def getInt(key: String): Int = {
    config.getInt(key)
  }

  def getLong(key: String): Long = {
    config.getLong(key)
  }


  def main(args: Array[String]): Unit = {
    val mongo = new MongoDao()
    println(mongo.queryLabelById("labelId", 3, "personas_label"))
  }

}
