package cn.enniu.spark.learnning.utils

import cn.enniu.spark.learnning.Settings
import org.apache.spark.SparkConf

/**
  * Created by xuwenjuan on 17/2/16.
  */
object SparkUtils {
  def getDefaultConf(appName: String): SparkConf = {
    val sparkConf = new SparkConf()
      .setAppName(appName)
      .setMaster(Settings.get("spark.master"))
    sparkConf
  }
}
