package cn.enniu.spark.learnning.dao

import org.mongodb.scala.Document

/**
  * Created by xuwenjuan on 17/2/16.
  */
trait Dao {

  def queryLabelById(labelFieldName: String, labelId: Long, collectionName: String): Seq[Document]
}
