package cn.enniu.spark.learnning.dao

import org.mongodb.scala.Document
import org.slf4j.LoggerFactory

/**
  * Created by xuwenjuan on 17/2/16.
  */
trait DaoProxy extends Dao {
  val logger = LoggerFactory.getLogger(this.getClass)

  abstract override def queryLabelById(labelFieldName: String, labelId: Long, collectionName: String): Seq[Document] = {
    val start = System.currentTimeMillis()
    val retVal = super.queryLabelById(labelFieldName, labelId, collectionName)
    val end = System.currentTimeMillis()
    logger.debug(s"collection :${collectionName},labelId:${labelId},use ${end - start} ms,current:${System.currentTimeMillis()}")
    retVal
  }


}
