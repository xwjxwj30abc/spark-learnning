package cn.enniu.spark.learnning.service

import cn.enniu.spark.learnning.DataPackage
import cn.enniu.spark.learnning.dao.{DaoProxy, MongoDao}
import org.mongodb.scala.bson.collection.immutable.Document

/**
  * Created by xuwenjuan on 17/2/16.
  */
class DataCollectionService {

  val mongoDao: MongoDao = new MongoDao with DaoProxy

  def collectionOriginData(labelId: Long): DataPackage = {
    val dp = new DataPackage(String.valueOf(labelId));
    dp.mongoData = readMongo(labelId)
    dp
  }

  def readMongo(labelId: Long): String = {
    var mongoData = Document.empty
    val collectionName = "personas_label"
    val results = mongoDao.queryLabelById("labelId", labelId, collectionName)
    if (results.size == 1) {
      mongoData ++= Document(collectionName -> results(0))
    } else {
      mongoData ++= Document(collectionName -> results)
    }
    mongoData.toJson
  }


}
